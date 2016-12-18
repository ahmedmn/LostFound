<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lostfound"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<lostfound:pagetemplate title="Create new post">
	<jsp:attribute name="head">
		<!-- Select2 -->
  		<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/select2/select2.min.css" />
	</jsp:attribute>


	<jsp:attribute name="body">
        <div class="row">
            <div class="col-md-12">
                <!-- form start -->
                <form:form method="post" action="${pageContext.request.contextPath}/post/new" modelAttribute="postCreate">

                    <div class="box box-primary" id="info-about-item">
                        <div class="box-header with-border">
                            <h3 class="box-title">Info about post</h3>
                        </div>

                        <div class="box-body">
                            <div class="form-group ${location_error?'has-error':''}"">
                              <label>Location</label>
                              <div class="input-group">
                                  <span class="input-group-addon"><i class="fa fa-location-arrow"></i></span>
                                  <form:input type="text" path="location" class="form-control" placeholder="Brno" />
                              </div>
                              <form:errors path="location" cssClass="help-block"/>
                            </div>

                            <div class="form-group">
                                <label>Type</label>
                                <div class="input-group ${type_error?'has-error':''}"">
                                    <form:select path="type" cssClass="form-control">
                                        <c:forEach items="${postTypes}" var="postType">
                                            <form:option value="${postType}">${postType}</form:option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                                <form:errors path="type" cssClass="help-block"/>
                            </div>
                        </div>
                        <!-- /.box-body -->
                    </div>

                    <!-- /.box -->
                    <div class="box box-success" id="info-actions">
                        <div class="box-header with-border">
                            <h3 class="box-title">Actions</h3>
                        </div>
                        <div class="box-body">
                            <button type="submit" id="add-item" class="btn btn-default">Add Item</button>
                            <button type="submit" class="btn btn-info">Submit</button>
                        </div>
                    </div>

                </form:form>
            </div>
        </div>



        <!-- /.box -->
        <div class="box box-success" id="item-info" style="display:none;">
              <div class="box-header with-border">
                  <h3 class="box-title">Item Info - #index#</h3>
              </div>
              <div class="box-body">



                  <div class="form-group">
                      <label>Name</label>
                      <div class="input-group">
                          <span class="input-group-addon"><i class="fa fa-file-text-o"></i></span>
                          <input type="text" path="name" class="form-control" placeholder=""/>
                      </div>
                  </div>



                  <div class="form-group">
                      <label>Description</label>
                      <div class="input-group">
                          <span class="input-group-addon"><i class="fa fa-file-text-o"></i></span>
                          <textarea class="form-control" rows="3" name="item-info-description[#index#][]" placeholder="Write your descrition of item"></textarea>
                      </div>
                  </div>
                  <div class="form-group">
                      <label>Images</label><a href="" class="add-image" data-index="" style="margin-left: 4px;"><i class="fa fa-plus-circle"></i></a>
                      <div class="input-group">
                          <span class="input-group-addon"><i class="fa fa-image"></i></span>
                          <input type="text" class="form-control" name="item-info-image[#index#][]" value="" placeholder="Set URL">
                      </div>
                  </div>
                  <div class="form-group">
                      <label>Keywords</label>
                      <div class="input-group">
                          <span class="input-group-addon"><i class="fa fa-file-text-o"></i></span>
                          <textarea class="form-control" rows="3" name="item-info-keywords[#index#][]" placeholder="Keyword per line"></textarea>
                      </div>
                  </div>
                  <div class="form-group">
                      <label>Categories</label>
                      <div class="input-group">
                          <span class="input-group-addon"><i class="fa fa-cog"></i></span>
                          <select class="form-control categories" multiple="multiple" name="item-info-categories[#index#][]" data-placeholder="Select a categories" style="width: 100%;">

                          </select>
                      </div>
                  </div>
              </div>
          </div>
    </jsp:attribute>













    <jsp:attribute name="footer">
        <!-- Select2 -->
        <script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
        <script type="text/javascript">
            $(function()
            {
                $(".select2").select2();

                var iterate = 0;
                var itemCopy = $("#item-info");
                var infoItem = $("#info-about-item");
                var infoActions = $("#info-actions");
                var elAddImage = $("#add-image");

                //INIT
                setValueOptions();
                addItem();

                function addItem()
                {
                    var copy = itemCopy.clone();

                    infoActions.before(copy);
                    copy.attr('id', 'categories-' + iterate);
                    copy.show();
                    $("#categories-" + iterate + " select.categories").select2();
                    replaceIndex(copy, iterate);
                    iterate++;
                }

                function replaceIndex(el, index)
                {
                    var title = el.find('h3');
                    var link = el.find('.add-image');

                    title.html(title.html().replace('#index#', index+1));
                    link.data('index', index);

                    el.find('.form-control').each(function()
                    {
                        var child = $(this);
                        child.attr('name', child.attr('name').replace('#index#', index));
                    });
                }

                function setValueOptions()
                {
                    $("select.categories option").each(function()
                    {
                        var el = $(this);
                        el.val(el.text());
                    });
                }

                function addImage(linkButton)
                {
                    var el = linkButton.next();
                    var clone = elAddImage.clone();
                    var input = clone.find('.form-control');
                    var index = linkButton.data('index');

                    el.parent().append(clone);

                    input.each(function()
                    {
                        var child = $(this);
                        child.attr('name', child.attr('name').replace('#index#', index));
                    });

                    clone.attr('id', '').show();
                }

                $("#add-item").click(function(e)
                {
                    e.preventDefault();
                    addItem();
                });

                $('body').delegate('.add-image', 'click', function(event)
                {
                    event.preventDefault();
                    addImage($(this));
                });

                $('body').delegate('.image-remove', 'click', function(event) {
                    event.preventDefault();
                    $(this).parent().parent().remove();
                })

            });
        </script>
    </jsp:attribute>



</lostfound:pagetemplate>
