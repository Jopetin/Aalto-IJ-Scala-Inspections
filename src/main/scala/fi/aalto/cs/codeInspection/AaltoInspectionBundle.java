package fi.aalto.cs.codeInspection;

import com.intellij.DynamicBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

public final class AaltoInspectionBundle extends DynamicBundle {
  @NonNls
  private static final String BUNDLE = "messages.AaltoInspectionBundle";

  private static final AaltoInspectionBundle INSTANCE = new AaltoInspectionBundle();

  private AaltoInspectionBundle() {
    super(BUNDLE);
  }

  @Nls
  public static String message(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key, @NotNull Object... params) {
    return INSTANCE.getMessage(key, params);
  }
}