package fi.aalto.cs.codeInspection

import com.intellij.codeInspection.{LocalInspectionTool, ProblemsHolder}
import org.jetbrains.plugins.scala.codeInspection.PsiElementVisitorSimple
import org.jetbrains.plugins.scala.lang.psi.api.base.literals.ScNullLiteral
import org.jetbrains.plugins.scala.lang.psi.api.expr.{ScMethodCall, ScReferenceExpression}
import org.jetbrains.plugins.scala.lang.psi.api.statements.ScFunctionDefinition

class NullInspection extends LocalInspectionTool:
  override def buildVisitor(
      holder: ProblemsHolder,
      isOnTheFly: Boolean
  ): PsiElementVisitorSimple = {
    case expr: ScNullLiteral =>
      holder.registerProblem(
        expr,
        AaltoInspectionBundle.message("inspection.null.usage.description")
      )
    case meth: ScMethodCall =>
      meth.getInvokedExpr match
        case ref: ScReferenceExpression =>
          ref.resolve() match
            case func: ScFunctionDefinition =>
              if func.annotations.nonEmpty then
                holder.registerProblem(
                  meth,
                  "Annotations"
                )
            case _ =>
        case _ =>
    case _ =>
  }
