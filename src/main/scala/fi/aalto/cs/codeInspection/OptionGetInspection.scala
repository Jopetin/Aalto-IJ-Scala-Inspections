package fi.aalto.cs.codeInspection

import org.jetbrains.plugins.scala.codeInspection.collections.{OperationOnCollectionInspection, Simplification, SimplificationType, invocation, isOption, likeOptionClasses}
import org.jetbrains.plugins.scala.lang.psi.api.expr.ScExpression

import scala.collection.immutable.ArraySeq

class OptionGetInspection extends OperationOnCollectionInspection {
  override def possibleSimplificationTypes: ArraySeq[SimplificationType] = ArraySeq(IllegalOptionGet)
}

object IllegalOptionGet extends SimplificationType {
  override def hint: String = AaltoInspectionBundle.message("inspection.option.get.usage.description")

  val getInvocation = invocation("get").from(likeOptionClasses)


  override def getSimplification(expr: ScExpression): Option[Simplification] = expr match {
    case getInvocation (qual) if isOption(qual) =>
      Some(replace(expr).withText(qual.getText).highlightFrom(qual))
    case _ => None
  }
}