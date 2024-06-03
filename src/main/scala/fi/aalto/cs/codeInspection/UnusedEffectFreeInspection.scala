package fi.aalto.cs.codeInspection

import com.intellij.codeInspection.{LocalInspectionTool, ProblemsHolder}
import com.intellij.psi.{PsiElement, PsiElementVisitor}
import org.jetbrains.plugins.scala.lang.psi.api.base.literals.ScNullLiteral
import org.jetbrains.plugins.scala.lang.psi.api.expr.{ScExpression, ScMethodCall}
/*
class CustomAnnotationInspection extends LocalInspectionTool:

  val annotationFinder: PartialFunction[PsiElement, Unit]

  override def buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor = {
    case meth: ScNullLiteral =>
      holder.registerProblem(
        meth,
        AaltoInspectionBundle.message("inspection.null.usage.description")
      )
    case _ =>
  }

*/