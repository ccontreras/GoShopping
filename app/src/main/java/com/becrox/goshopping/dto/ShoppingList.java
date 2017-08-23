package com.becrox.goshopping.dto;

/**
 * @author cconTreras
 */

public class ShoppingList {

  private String mTitle;

  public ShoppingList() {
  }

  public ShoppingList(String title) {
    mTitle = title;
  }

  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String title) {
    mTitle = title;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ShoppingList list = (ShoppingList) o;

    return mTitle != null ? mTitle.equals(list.mTitle) : list.mTitle == null;
  }

  @Override public int hashCode() {
    return mTitle != null ? mTitle.hashCode() : 0;
  }

  public static class Builder {
    private String mTitle;

    public Builder withTitle(String title) {
      mTitle = title;
      return this;
    }

    public ShoppingList build() {
      return new ShoppingList(mTitle);
    }
  }
}
