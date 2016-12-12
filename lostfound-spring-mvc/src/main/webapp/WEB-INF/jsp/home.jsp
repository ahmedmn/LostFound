<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"
	trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<my:pagetemplate title="Lost & Found">

	<jsp:attribute name="head">
		<!-- Select2 -->
  		<link rel="stylesheet" href="/lostfound/plugins/select2/select2.min.css" />
	</jsp:attribute>

	<jsp:attribute name="footer">
		<!-- Select2 -->
		<script src="/lostfound/plugins/select2/select2.full.min.js"></script>
		<script type="text/javascript">
			$(function()
			{
				$(".select2").select2();
			});
		</script>
	</jsp:attribute>

	<jsp:attribute name="body">

		<div class="box box-default">
			<div class="box-header with-border">
				<h3 class="box-title">Find post</h3>

				<div class="box-tools pull-right">
					<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
					<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-remove"></i></button>
				</div>
			</div>
			<!-- /.box-header -->
			<div class="box-body">
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<label>Select filter</label>
							<select class="form-control select2" style="width: 100%;">
								<option value="0">Type</option>
								<option value="1">Location</option>
								<option value="2">Username</option>
								<option value="3">Date</option>
								<option value="4" selected="selected">Keywords</option>
							</select>
						</div>
						<!-- /.form-group -->
					</div>
				</div>
				<!-- /.row -->
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-header">
						<h3 class="box-title">Posts</h3>
					</div>
					<!-- /.box-header -->
					<div class="box-body table-responsive no-padding">
						<table class="table table-hover">
							<tbody><tr>
								<th>ID</th>
								<th>User</th>
								<th>Date</th>
								<th>Status</th>
								<th>Reason</th>
							</tr>
							<tr>
								<td>183</td>
								<td>John Doe</td>
								<td>11-7-2014</td>
								<td><span class="label label-success">Approved</span></td>
								<td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
							</tr>
							<tr>
								<td>219</td>
								<td>Alexander Pierce</td>
								<td>11-7-2014</td>
								<td><span class="label label-warning">Pending</span></td>
								<td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
							</tr>
							<tr>
								<td>657</td>
								<td>Bob Doe</td>
								<td>11-7-2014</td>
								<td><span class="label label-primary">Approved</span></td>
								<td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
							</tr>
							<tr>
								<td>175</td>
								<td>Mike Doe</td>
								<td>11-7-2014</td>
								<td><span class="label label-danger">Denied</span></td>
								<td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
							</tr>
							</tbody></table>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
		</div>

	</jsp:attribute>
</my:pagetemplate>
