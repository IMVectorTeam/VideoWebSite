<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<body>
<h4>User</h4>
<a href="getUser?id=20154070">查询用户信息</a>
<a href="getUserByEmail?email=tajkun@163.com">通过邮箱查询用户信息</a>
<a href="getUserByVideoId?id=1003">根据videoId查询该上传用户的信息</a>
<a href="insertUser">插入用户</a>
<a href="getUserList">得到用户list</a>
<a href="updateUser">更新用户信息</a>
<a href="deleteUser?id=qwer">删除用户</a>

<h4>Administrator</h4>
<a href="getAdministrator?id=0001">得到管理员信息</a>
<a href="getAdministratorByEmail?email=zhuzhu@163.com">通过邮箱得到管理员信息</a>
<a href="updateAdministrator">更新管理员信息</a>

<h4>VideoCategory</h4>
<a href="getVideoCategory?id=1">得到视频类别信息</a>
<a href="insertVideoCategory">添加视频类别</a>
<a href="getVideoCategoryList">得到视频类别list</a>
<a href="updateCategory">更新视频类别信息</a>
<a href="deleteCategory?id=3">删除视频类别</a>


<h4>Video</h4>
<a href="getVideo?id=1002">得到单个视频信息</a>
<a href="getVideoList">得到视频list</a>
<a href="insertVideo">插入单个视频</a>
<a href="updateVideo">更新视频信息</a>
<a href="deleteVideo?id=1010">删除视频</a>
<a href="getVideoListByCategory?id=4">得到分类视频</a>
<a href="getVideoListNumber?id=1">得到某类视频数目</a>
<a href="getVideoListByCategoryName?name=大型演唱会">通过视频类别名称查视频list</a>
<a href="getVideoListByUserId?id=20154070">根据用户id查询该用户的所有的上传的视频</a>
<a href="getVideoListByLikeName?name=未">模糊查询</a>


<h4>Message</h4>
<a href="getMessageByVideoId?id=1002">按视频查询评论</a>
<a href="insertMessage">添加评论</a>
<a href="deleteMessage?id=2003">删除评论</a>
</body>
</html>
