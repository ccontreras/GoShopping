package com.becrox.goshopping.injection.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * @author cconTreras
 */

@Scope @Retention(RetentionPolicy.RUNTIME) public @interface PerFragment {
}
