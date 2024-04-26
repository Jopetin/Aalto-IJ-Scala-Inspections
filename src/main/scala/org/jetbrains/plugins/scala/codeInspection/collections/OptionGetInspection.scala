package org.jetbrains.plugins.scala.codeInspection.collections

import org.jetbrains.plugins.scala.lang.psi.api.expr.ScExpression
import scala.collection.immutable.ArraySeq

class OptionGetInspection extends OperationOnCollectionInspection {
  override def possibleSimplificationTypes: ArraySeq[SimplificationType] = ArraySeq(IllegalOptionGet)
}

object IllegalOptionGet extends SimplificationType {
  override def hint: String = "Using `get` to fetch a value from Option can cause runtime exceptions"


  override def getSimplification(expr: ScExpression): Option[Simplification] = expr match {
    case `.get`(qual) if isOption(qual) =>
      Some(replace(expr).withText(qual.getText).highlightFrom(qual))
    case _ => None
  }
}
