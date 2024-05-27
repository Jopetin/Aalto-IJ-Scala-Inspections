package fi.aalto.cs.codeInspection

import org.jetbrains.plugins.scala.codeInspection.collections.{
  OperationOnCollectionInspection,
  Simplification,
  SimplificationType,
  invocation,
  isOption,
  likeCollectionClasses
}
import org.jetbrains.plugins.scala.lang.psi.api.expr.ScExpression

import scala.collection.immutable.ArraySeq

class OptionHeadOrLastInspection extends OperationOnCollectionInspection:
  override def possibleSimplificationTypes: ArraySeq[SimplificationType] = ArraySeq(
    IllegalOptionHeadOrLast
  )

/** Inspection object for recognizing either Option.head or Option.last usage
  *
  * Utilizes existing methods from the Scala plugin
  */
object IllegalOptionHeadOrLast extends SimplificationType:
  private val headOrLastInvocation = invocation(Set("head", "last")).from(likeCollectionClasses)

  override def hint: String =
    AaltoInspectionBundle.message("inspection.option.head.or.last.usage.description")

  override def getSimplification(expr: ScExpression): Option[Simplification] = expr match
    case headOrLastInvocation(qual) if isOption(qual) =>
      Some(replace(expr).withText(qual.getText).highlightFrom(qual))
    case _ => None
