package fi.aalto.cs.codeInspection

import com.intellij.codeInspection.{LocalInspectionTool, ProblemsHolder}
import org.jetbrains.plugins.scala.codeInspection.PsiElementVisitorSimple
import org.jetbrains.plugins.scala.lang.psi.api.base.literals.ScNullLiteral

<<<<<<< HEAD

class NullInspection extends LocalInspectionTool {
  override def buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitorSimple = {
=======
class NullInspection extends LocalInspectionTool:
  override def buildVisitor(
      holder: ProblemsHolder,
      isOnTheFly: Boolean
  ): PsiElementVisitorSimple = {
>>>>>>> review
    case expr: ScNullLiteral =>
      holder.registerProblem(
        expr,
        AaltoInspectionBundle.message("inspection.null.usage.description")
      )
    case _ =>
  }
<<<<<<< HEAD
}
=======
>>>>>>> review
