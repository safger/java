package com.sn.entity;

import java.util.ArrayList;
import java.util.List;


public class Menu  {
	 
	
		private java.lang.String fuid;
		private java.lang.String menuName;
		private java.lang.String menuParentid;
		private java.lang.String menuUrl;
		private java.lang.Integer menuOrder;
		private java.lang.String images;
		private java.util.Date createdate;
		private java.util.Date modifydate;
		private java.lang.String createuserrealname;
		private java.lang.String createuserid;
		private java.lang.String modifyuserrealname;
		private java.lang.String modifyuserid;
		
		//---------tree
		private String id;
		private String text;
		private Boolean checked;
		private Boolean hasChild;
		private String state;
		//--------------
		private List<Operating> Operating_list;
		
		private List<Object> children=new ArrayList<Object>();
		
		
		public void setFuid(java.lang.String value) {
			this.fuid = value;
		}
		
		public java.lang.String getFuid() {
			return this.fuid;
		}
		public void setMenuName(java.lang.String value) {
			this.menuName = value;
		}
		
		public java.lang.String getMenuName() {
			return this.menuName;
		}
		public void setMenuParentid(java.lang.String value) {
			this.menuParentid = value;
		}
		
		public java.lang.String getMenuParentid() {
			return this.menuParentid;
		}
		public void setMenuUrl(java.lang.String value) {
			this.menuUrl = value;
		}
		
		public java.lang.String getMenuUrl() {
			return this.menuUrl;
		}
		public void setMenuOrder(java.lang.Integer value) {
			this.menuOrder = value;
		}
		
		public java.lang.Integer getMenuOrder() {
			return this.menuOrder;
		}
		public void setImages(java.lang.String value) {
			this.images = value;
		}
		
		public java.lang.String getImages() {
			return this.images;
		}
		public void setCreatedate(java.util.Date value) {
			this.createdate = value;
		}
		
		public java.util.Date getCreatedate() {
			return this.createdate;
		}
		public void setModifydate(java.util.Date value) {
			this.modifydate = value;
		}
		
		public java.util.Date getModifydate() {
			return this.modifydate;
		}
		public void setCreateuserrealname(java.lang.String value) {
			this.createuserrealname = value;
		}
		
		public java.lang.String getCreateuserrealname() {
			return this.createuserrealname;
		}
		public void setCreateuserid(java.lang.String value) {
			this.createuserid = value;
		}
		
		public java.lang.String getCreateuserid() {
			return this.createuserid;
		}
		public void setModifyuserrealname(java.lang.String value) {
			this.modifyuserrealname = value;
		}
		
		public java.lang.String getModifyuserrealname() {
			return this.modifyuserrealname;
		}
		public void setModifyuserid(java.lang.String value) {
			this.modifyuserid = value;
		}
		
		public java.lang.String getModifyuserid() {
			return this.modifyuserid;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public Boolean getChecked() {
			return checked;
		}

		public void setChecked(Boolean checked) {
			this.checked = checked;
		}

		public Boolean getHasChild() {
			return hasChild;
		}

		public void setHasChild(Boolean hasChild) {
			this.hasChild = hasChild;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public List<Operating> getOperating_list() {
			return Operating_list;
		}

		public void setOperating_list(List<Operating> operating_list) {
			Operating_list = operating_list;
		}

		public List<Object> getChildren() {
			return children;
		}

		public void setChildren(List<Object> children) {
			this.children = children;
		}
	

}	 

