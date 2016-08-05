package com.sn.entity;

import java.util.ArrayList;
import java.util.List;


public class Role  {
	 
	
		private java.lang.String fuid;
		private java.lang.String systemid;
		private java.lang.String organizeid;
		private java.lang.String code;
		private java.lang.String realname;
		private java.lang.String category;
		private java.lang.Integer sortcode;
		private java.lang.Integer deletemark;
		private java.lang.Integer enabled;
		private java.lang.String description;
		private java.util.Date createdate;
		private java.lang.String createuserid;
		private java.lang.String createuserrealname;
		private java.util.Date modifydate;
		private java.lang.String modifyuserid;
		private java.lang.String modifyuserrealname;
		private List<Object> children=new ArrayList<Object>();

		public void setFuid(java.lang.String value) {
			this.fuid = value;
		}
		
		public java.lang.String getFuid() {
			return this.fuid;
		}
		public void setSystemid(java.lang.String value) {
			this.systemid = value;
		}
		
		public java.lang.String getSystemid() {
			return this.systemid;
		}
		public void setOrganizeid(java.lang.String value) {
			this.organizeid = value;
		}
		
		public java.lang.String getOrganizeid() {
			return this.organizeid;
		}
		public void setCode(java.lang.String value) {
			this.code = value;
		}
		
		public java.lang.String getCode() {
			return this.code;
		}
		public void setRealname(java.lang.String value) {
			this.realname = value;
		}
		
		public java.lang.String getRealname() {
			return this.realname;
		}
		public void setCategory(java.lang.String value) {
			this.category = value;
		}
		
		public java.lang.String getCategory() {
			return this.category;
		}
		public void setSortcode(java.lang.Integer value) {
			this.sortcode = value;
		}
		
		public java.lang.Integer getSortcode() {
			return this.sortcode;
		}
		public void setDeletemark(java.lang.Integer value) {
			this.deletemark = value;
		}
		
		public java.lang.Integer getDeletemark() {
			return this.deletemark;
		}
		public void setEnabled(java.lang.Integer value) {
			this.enabled = value;
		}
		
		public java.lang.Integer getEnabled() {
			return this.enabled;
		}
		public void setDescription(java.lang.String value) {
			this.description = value;
		}
		
		public java.lang.String getDescription() {
			return this.description;
		}
		public void setCreatedate(java.util.Date value) {
			this.createdate = value;
		}
		
		public java.util.Date getCreatedate() {
			return this.createdate;
		}
		public void setCreateuserid(java.lang.String value) {
			this.createuserid = value;
		}
		
		public java.lang.String getCreateuserid() {
			return this.createuserid;
		}
		public void setCreateuserrealname(java.lang.String value) {
			this.createuserrealname = value;
		}
		
		public java.lang.String getCreateuserrealname() {
			return this.createuserrealname;
		}
		public void setModifydate(java.util.Date value) {
			this.modifydate = value;
		}
		
		public java.util.Date getModifydate() {
			return this.modifydate;
		}
		public void setModifyuserid(java.lang.String value) {
			this.modifyuserid = value;
		}
		
		public java.lang.String getModifyuserid() {
			return this.modifyuserid;
		}
		public void setModifyuserrealname(java.lang.String value) {
			this.modifyuserrealname = value;
		}
		
		public java.lang.String getModifyuserrealname() {
			return this.modifyuserrealname;
		}

		public List<Object> getChildren() {
			return children;
		}

		public void setChildren(List<Object> children) {
			this.children = children;
		}
	

}	 

