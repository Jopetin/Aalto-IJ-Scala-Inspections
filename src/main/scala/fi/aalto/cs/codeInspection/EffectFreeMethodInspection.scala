package fi.aalto.cs.codeInspection

import com.intellij.codeInspection.{LocalInspectionTool, ProblemsHolder}
import com.intellij.psi.PsiElement
import org.jetbrains.plugins.scala.codeInspection.PsiElementVisitorSimple
import org.jetbrains.plugins.scala.lang.psi.api.expr.{
  ScExpression,
  ScMethodCall,
  ScReferenceExpression
}
import org.jetbrains.plugins.scala.lang.psi.api.statements.ScFunctionDefinition

class EffectFreeMethodInspection extends LocalInspectionTool:

  override def buildVisitor(
      holder: ProblemsHolder,
      isOnTheFly: Boolean
  ): PsiElementVisitorSimple = {
    case method: ScMethodCall =>
      method.getInvokedExpr match
        case ref: ScReferenceExpression =>
          ref.resolve() match
            case func: ScFunctionDefinition =>
              if func.annotations.map(_.getText).contains(AaltoInspectionBundle.message("inspection.effect.free.method.annotation.name"))
                && !method.getParent.isInstanceOf[ScReferenceExpression]
                && method.getParent.getLastChild != method
              then
                holder.registerProblem(
                  method,
                  AaltoInspectionBundle.message("inspection.effect.free.method.description")
                )
            case _ =>
        case _ =>
    case _ =>
  }
