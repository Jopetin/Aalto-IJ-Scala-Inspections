package org.jetbrains.plugins.scala.codeInspection.syntaticClarification

import com.intellij.codeInspection.{LocalInspectionTool, ProblemsHolder}
import org.jetbrains.plugins.scala.codeInspection.PsiElementVisitorSimple
import org.jetbrains.plugins.scala.lang.psi.api.base.literals.ScNullLiteral
import org.jetbrains.plugins.scala.codeInspection.AaltoInspectionBundle


class NullInspection extends LocalInspectionTool {
  override def buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitorSimple = {
    case expr: ScNullLiteral =>
      holder.registerProblem(
        expr,
        AaltoInspectionBundle.message("inspection.null.usage.description")
      )
    case _ =>
  }
}