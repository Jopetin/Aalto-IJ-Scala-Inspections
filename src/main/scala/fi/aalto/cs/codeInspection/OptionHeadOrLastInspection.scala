package fi.aalto.cs.codeInspection

import org.jetbrains.plugins.scala.codeInspection.collections.{OperationOnCollectionInspection, Simplification, SimplificationType, invocation, isOption, likeCollectionClasses}
import org.jetbrains.plugins.scala.codeInspection.collections
import org.jetbrains.plugins.scala.lang.psi.api.expr.ScExpression

import scala.collection.immutable.ArraySeq

class OptionHeadOrLastInspection extends OperationOnCollectionInspection {
  override def possibleSimplificationTypes: ArraySeq[SimplificationType] = ArraySeq(IllegalOptionHeadOrLast)
}

object IllegalOptionHeadOrLast extends SimplificationType {
  override def hint: String = AaltoInspectionBundle.message("inspection.option.head.or.last.usage.description")

  val headOrLastInvocation = invocation(Set("head", "tail")).from(likeCollectionClasses)

  override def getSimplification(expr: ScExpression): Option[Simplification] = expr match {
    case headOrLastInvocation (qual) if isOption(qual) =>
      Some(replace(expr).withText(qual.getText).highlightFrom(qual))
    case _ => None
  }
}