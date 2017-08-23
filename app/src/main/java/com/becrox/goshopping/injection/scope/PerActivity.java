package com.becrox.goshopping.injection.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Cesar on 19/8/17.
 */

@Scope @Retention(RetentionPolicy.RUNTIME) public @interface PerActivity {
}
