package org.jetbrains.plugins.scala.codeInspection.collections

import org.jetbrains.plugins.scala.lang.psi.api.expr.ScExpression
import scala.collection.immutable.ArraySeq
import org.jetbrains.plugins.scala.codeInspection.AaltoInspectionBundle

class OptionGetInspection extends OperationOnCollectionInspection {
  override def possibleSimplificationTypes: ArraySeq[SimplificationType] = ArraySeq(IllegalOptionGet)
}

object IllegalOptionGet extends SimplificationType {
  override def hint: String = AaltoInspectionBundle.message("inspection.illegal.option.get.usage.description")


  override def getSimplification(expr: ScExpression): Option[Simplification] = expr match {
    case `.get`(qual) if isOption(qual) =>
      Some(replace(expr).withText(qual.getText).highlightFrom(qual))
    case _ => None
  }
}