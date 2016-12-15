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
  		<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/select2/select2.min.css" />
		  <!-- daterange picker -->
		  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
		  <!-- bootstrap datepicker -->
		  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
	</jsp:attribute>

	<jsp:attribute name="footer">
		<!-- date-range-picker -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
		<!-- bootstrap datepicker -->
		<script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
		<!-- Select2 -->
		<script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>

		<script type="text/javascript">
			$(function()
			{
				var select = $(".select2");
				var itemSelect = $('.item-select');
				$('#dateRange').daterangepicker({ "opens": "right"});
				select.select2();
				showRightInput();

				select.change(showRightInput)

				function showRightInput()
				{
					var value = select.val();
					console.log(value + "VALUE");
					itemSelect.hide();

					switch (value)
					{
						case "0":
							$('#item-type').show();
							break;
						case "1":
							$('#item-location').show();
							break;
						case "2":
							$('#item-user').show();
							break;
						case "3":
							$('#item-date').show();
							break;
						case "4":
							$('#item-keywords').show();
							break;
					}
				}
			});
		</script>
	</jsp:attribute>

	<jsp:attribute name="body">

		<form method="GET" action="/">
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
				<div class="row">
					<div class="col-md-12">
						<div class="item-select form-group" id="item-keywords" style="display: none;">
							<label>Keywords</label>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-file-text-o"></i></span>
								<textarea class="form-control" rows="3" name="search-value" value="" placeholder="Keyword per line"></textarea>
							</div>
						</div>

						<div class="item-select form-group" id="item-location" style="display: none;">
							<label>Location</label>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-location-arrow"></i></span>
								<input type="text" class="form-control" name="search-value" value="Brno" placeholder="">
							</div>
						</div>

						<div class="item-select form-group" id="item-user" style="display: none;">
							<label>User name</label>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user"></i></span>
								<input type="text" class="form-control" name="search-value" value="" placeholder="">
							</div>
						</div>

						<div class="item-select form-group" id="item-type" style="display: none;">
							<label>Type</label>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-chevron-circle-down"></i></span>
								<select class="form-control select2" name="search-value" style="width: 100%;">
									<option selected="selected" value="0">Lost</option>
									<option value="1">Found</option>
								</select>
							</div>
						</div>

						<div class="item-select form-group" id="item-date" style="display: none;">
							<label>Date range:</label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" name="search-value" class="form-control pull-right" id="dateRange">
							</div>
						</div>
					</div>
				</div>
				<!-- /.row -->
				<button type="submit" name="search" id="search-btn" class="btn btn-primary"><i class="fa fa-search"></i>
				</button>

			</div>
		</div>
		</form>

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
