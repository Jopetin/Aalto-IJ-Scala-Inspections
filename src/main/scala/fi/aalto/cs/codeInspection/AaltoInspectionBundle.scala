package fi.aalto.cs.codeInspection

import com.intellij.DynamicBundle
import org.jetbrains.annotations.{Nls, NonNls, NotNull, PropertyKey}

/**
 * Bundling object that allows inspection code to access values in the message bundle
 */
object AaltoInspectionBundle:
  @NonNls
  private final val bundlePath = "messages.AaltoInspectionBundle"

  @Nls
  def message(
      @NotNull @PropertyKey(resourceBundle = bundlePath) key: String,
      @NotNull params: AnyRef*
  ): String =
    bundle.getMessage(key, params*)

  private object bundle extends DynamicBundle(bundlePath)
