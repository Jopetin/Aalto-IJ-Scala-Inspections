package fi.aalto.cs.codeInspection

import com.intellij.codeInspection.options.OptPane
import com.intellij.codeInspection.options.OptPane.{pane, string}
import com.intellij.codeInspection.{LocalInspectionTool, ProblemsHolder}
import com.intellij.psi.PsiElement
import org.jetbrains.plugins.scala.codeInspection.PsiElementVisitorSimple
import org.jetbrains.plugins.scala.lang.psi.api.expr.{
  ScArgumentExprList,
  ScExpression,
  ScMethodCall,
  ScParenthesisedExpr,
  ScReferenceExpression
}
import org.jetbrains.plugins.scala.lang.psi.api.statements.ScFunctionDefinition

import scala.beans.BeanProperty

/** Inspection used to check whether results from methods with given annotation are used in any way
  *
  * Essentially when finding a method call to a method with the given annotation, it checks whether
  * it is the last child of its parent in the Psi tree. Annotation is identified based on its name,
  * which can be configured in the message bundle. If it is set to a variable or returned by the
  * function, the last line will be the method call itself. Cases where the method return value is
  * used as a parameter or chained to an another method call are checked through the class of the
  * parent element.
  */
class SideEffectFreeMethodInspection extends LocalInspectionTool:

  import org.jetbrains.plugins.scala.codeInspection.ui.CompilerInspectionOptions.*

  @BeanProperty
  var annotationName: String =
    AaltoInspectionBundle.message("inspection.effect.free.method.annotation.name")

  override def getOptionsPane: OptPane = pane(
    string(
      "annotationName",
      AaltoInspectionBundle.message("inspection.effect.free.method.annotation.description")
    )
  )

  override def buildVisitor(
      holder: ProblemsHolder,
      isOnTheFly: Boolean
  ): PsiElementVisitorSimple = {
    case method: ScMethodCall =>
      method.getInvokedExpr match
        case ref: ScReferenceExpression =>
          ref.resolve() match
            case func: ScFunctionDefinition =>
              if func.annotations.exists(
                  _.textMatches(annotationName)
                )
              then
                var target: PsiElement          = method
                var parent: PsiElement          = method.getParent
                var siblings: Array[PsiElement] = parent.getChildren

                // Unwrap all parenthesis
                while parent.isInstanceOf[ScParenthesisedExpr] do
                  target = parent
                  parent = parent.getParent
                  siblings = parent.getChildren
                // In case a code block is wrapped in brackets we compare to the last element before the closing bracket
                if siblings.last.textMatches("}") && siblings.length > 1 then
                  siblings = siblings.dropRight(1)
                // Check whether the method is the last in its block...
                if siblings.last != target
                  // ...or is chained to an another method call...
                  && !parent.isInstanceOf[ScReferenceExpression]
                  // ...or is used as a parameter
                  && !parent.isInstanceOf[ScArgumentExprList]
                then
                  holder.registerProblem(
                    // Highlights only the method call in question
                    method.getFirstChild.getLastChild,
                    AaltoInspectionBundle.message("inspection.effect.free.method.description")
                  )
            case _ =>
        case _ =>
    case _ =>
  }
