<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<%=path %>/static/js/plugin/autocomplete/jquery-ui.css" type="text/css">
    <script type="text/javascript" src="<%=path%>/static/js/plugin/marked/marked.js"></script>
    <script type="text/javascript" src="<%=path%>/static/js/plugin/marked/editor.js"></script>
    <link rel="stylesheet" href="<%=path%>/static/css/editor.css" />
    <title>Editor</title>
    <style type="text/css">
        body {
            padding: 0;
            margin: 0;
            background-color: #f9f9f7;
        }
        .editor-wrapper {
            max-width: 680px;
            padding: 10px;
            margin: 60px auto;
        }
    </style>
</head>
<body>
<div class="editor-wrapper">
    <input class="title" type="text" placeholder="填写一个牛B的标题吧" />
    <textarea id="editor" placeholder="Content here ...."></textarea>
    <div style="margin: 5px 0px 5px 0px;">
        <input type="text" id="project">&nbsp;
        <input type="button" onclick="affix();" value="贴上" >
        <input type="hidden" id="j_label" name="label" value="">
        <div id="j_label_txt"></div>
    </div>
    <input class="btn" type="button" value="发布" onclick="submit();" />
</div>
<script type="text/javascript" src="<%=path%>/static/js/plugin/marked/zepto.min.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/plugin/ueditor/third-party/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/static/js/plugin/autocomplete/jquery-ui.js"></script>
<script type="text/javascript">
    var editor = new Editor();
    (function($) {
        $('#editor').load('<%=path%>/static/markdown.md',
            function(data) {
                editor.render();
            }
        );
    })(Zepto);

    //表单提交
    function submit() {
        $('.btn').attr('disabled', true).text('\u6B63\u5728\u63D0\u4EA4...');
        var title = $('.title').val(), j_label = $('#j_label').val(), content = editor.codemirror.getValue();
        if (title == null || title == '') {
            alert('请填写标题');
            $('.title').focus();
            $('.btn').removeAttr('disabled').text('\u63D0\u4EA4');
            return;
        }
        if (content == null || content == '' || editor.codemirror.getValue().length == 720) {
            alert('请填写有实际意义的内容');
            $('#editor').focus();
            $('.btn').removeAttr('disabled').text('\u63D0\u4EA4');
            return;
        }
        //alert(editor.codemirror.getValue().length);
        if (j_label == null || j_label == '') {
            alert('请填写文章对应的标签');
            $('#project').focus();
            $('.btn').removeAttr('disabled').text('\u63D0\u4EA4');
            return;
        } else if (j_label.indexOf('，') != -1) {
            alert('多个标签之间请用英文逗号分隔');
            $('#project').focus();
            $('.btn').removeAttr('disabled').text('\u63D0\u4EA4');
            return;
        }
        $.post('<%=path %>/user/share', {title:title, label: j_label, content:content}, function(data){
            if (data.status == '0000') {
                window.location.href = '<%=path %>/user/' + data.appendInfo;
            } else {
                alert(data.message);
            }
        });
    }

    //自动补全
    $(function() {
        var cache = {};
        $("#project").autocomplete({
            minLength : 2,
            source : function(request, response) {
                var term = request.term;
                if (term in cache) {
                    response(cache[term]);
                    return;
                }
                var str = "term=" + encodeURI(encodeURI(term));
                $.getJSON("<%=path %>/user/findLabel", str, function(data, status, xhr) {
                    cache[term] = data;
                    response(data);
                });
            }
        });
    });

    //添加标签
    function affix() {
        var project = $("#project").val();
        if (project == null || project == '') {
            alert('\u8001\u5144\uFF0C\u4F60\u8FD9\u662F\u5728\u9017\u6211\u5417\uFF1F');
        } else {
            var subject = $('#j_label').val(), str = '';
            var ary = subject.split(',');
            for (var i = 0; i < ary.length; i++) {
                if (project == ary[i]) {
                    alert('\u4F60\u5DF2\u7ECF\u52A0\u4E86\u4E00\u4E2A\u76F8\u540C\u7684\u6807\u7B7E\u4E86');
                    return;
                }
            }
            if (subject == null || subject == '') {
                str = project;
            } else {
                str = subject + "," + project;
            }
            $('#j_label').val(str);
            $('#j_label_txt').append('<span class="label">' + project + '</span>&nbsp;');
            $("#project").val('');
        }
    }
</script>
</body>
</html>