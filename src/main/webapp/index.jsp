<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html;
                         charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8">
    <script src="http://code.jquery.com/jquery-2.2.4.js"
            type="text/javascript"></script>
    <script>
        function GetAjax() {
            $('#getbtn').click(function () {
                $.ajax({
                    url: "/JavaServlet/",
                    data: {
                        userName: $('#userName').val()
                    },
                    success: function (response) {
                        $('#ajaxUserServletResponse').text(response);
                    }
                });
            });
        }
        function PostAjax() {
            $('#postbtn').click(function () {
                $.ajax({
                    type: "POST",
                    url: "/JavaServlet/",
                    data: {
                        userName: $('#userName').val()
                    },
                    success: function (response) {
                        $('#ajaxUserServletResponse').text(response);
                    }
                });
            });
        }
        function PutAjax() {
            $('#putbtn').click(function () {
                $.ajax({
                    type: "PUT",
                    url: "/JavaServlet/",
                    data: $('#userName').val(),
                    success: function (response) {
                        $('#ajaxUserServletResponse').text(response);
                    }
                });
            });
        }
        function DeleteAjax() {
            $('#deletebtn').click(function () {
                $.ajax({
                    type: "DELETE",
                    url: "/JavaServlet/",
                    data: $('#userName').val(),
                    success: function (response) {
                        $('#ajaxUserServletResponse').text(response);
                    }
                });
            });
        }

        $(document).ready(GetAjax).ready(PostAjax).ready(PutAjax).ready(DeleteAjax);
    </script>
</head>
<body>
        Input: <input type="text" id="userName"/><br/>
    <div id="hello">
        <button type="button" onclick="GetAjax()" id="getbtn">GET</button>
        <button type="button" onclick="PostAjax()" id="postbtn">POST</button>
        <button type="button" onclick="PutAjax()" id="putbtn">PUT</button>
        <button type="button" onclick="DeleteAjax()" id="deletebtn">DELETE</button>

    </div>
    <strong>Response of servlet </strong>:
    <span id="ajaxUserServletResponse"></span>
        <table>
            <c:forEach items="${list}" var="list">
                <tr>
                    <td>${list}</td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>
