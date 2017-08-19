package com.becrox.goshopping.domain.dto;

/**
 * @author cconTreras
 */

public class List {
    private String mId;
    private String mTitle;

    public List() {
    }

    public List(String id, String title) {
        mId = id;
        mTitle = title;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        List list = (List) o;

        return mTitle != null ? mTitle.equals(list.mTitle) : list.mTitle == null;
    }

    @Override
    public int hashCode() {
        return mTitle != null ? mTitle.hashCode() : 0;
    }

    public static class Builder {
        private String mId;
        private String mTitle;

        public Builder withId(String id) {
            mId = id;
            return this;
        }

        public Builder withTitle(String title) {
            mTitle = title;
            return this;
        }

        public List build() {
            return new List(mId, mTitle);
        }
    }
}
