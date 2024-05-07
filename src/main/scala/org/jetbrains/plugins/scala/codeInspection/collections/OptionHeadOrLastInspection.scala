package org.jetbrains.plugins.scala.codeInspection.collections

import org.jetbrains.plugins.scala.lang.psi.api.expr.ScExpression
import scala.collection.immutable.ArraySeq
import org.jetbrains.plugins.scala.codeInspection.AaltoInspectionBundle

class OptionHeadOrLastInspection extends OperationOnCollectionInspection {
  override def possibleSimplificationTypes: ArraySeq[SimplificationType] = ArraySeq(IllegalOptionHeadOrLast)
}

object IllegalOptionHeadOrLast extends SimplificationType {
  override def hint: String = AaltoInspectionBundle.message("inspection.option.head.or.last.usage.description")


  override def getSimplification(expr: ScExpression): Option[Simplification] = expr match {
    case `.head`(qual) if isOption(qual) =>
      Some(replace(expr).withText(qual.getText).highlightFrom(qual))
    case `.last`(qual) if isOption(qual) =>
      Some(replace(expr).withText(qual.getText).highlightFrom(qual))
    case _ => None
  }
}