<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <title>Kiem Ke</title>
</head>
<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper h-100">

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                               aria-label="Search" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button">
                                <i class="fas fa-search fa-sm"></i>
                            </button>
                        </div>
                    </div>
                </form>

                <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                <div class="nav-item dropdown no-arrow d-sm-none">
                    <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-search fa-fw"></i>
                    </a>
                    <!-- Dropdown - Messages -->
                    <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                         aria-labelledby="searchDropdown">
                        <form class="form-inline mr-auto w-100 navbar-search">
                            <div class="input-group">
                                <input type="text" class="form-control bg-light border-0 small"
                                       placeholder="Search for..." aria-label="Search"
                                       aria-describedby="basic-addon2">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="button">
                                        <i class="fas fa-search fa-sm"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid ">

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">Kiểm kho</h1>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3 d-flex justify-content-between align-items-center">
                        <h4 class="m-0 font-weight-bold text-primary">Phiếu kiểm kê</h4>
                        <button id="specialButton" class="btn btn-success">
                            Tạo phiếu kiểm kê
                        </button>
                    </div>
                    <div class="card-body" id="phieuKiemKe">
                        <div class="form-group">
                            <label for="exampleName">
                                <h4 class="font-weight-bold">Tên phiếu kiểm kê</h4>
                            </label>
                            <input class="form-control" id="exampleName"
                                   placeholder="Nhập tên kiểm kê">
                            <small id="emailHelp" class="form-text text-muted">Nhập tên phiếu để dễ dàng truy vấn
                                hơn!</small>
                        </div>
                        <div>
                            <table class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Mã nhập</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Số lượng lý thuyết</th>
                                    <th>Số lượng thực tế</th>
                                    <th>Chức năng</th>
                                </tr>
                                </thead>
                                <tbody id="noiDungKiemKe">
                                </tbody>
                            </table>
                            <button type="submit" id="kiemKeSubmit" class="d-block ml-auto btn btn-success">Xác nhận
                            </button>
                        </div>


                    </div>
                </div>


                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Bảng hàng hóa</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">

                            <table class="table table-bordered table-striped" id="dataTable">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Số lượng</th>
                                    <th>Tiền nhập (nghìn)</th>

                                    <th>Trạng thái</th>
                                    <th>Mã nhập</th>
                                    <th>Ngày nhập</th>
                                    <th>Chức năng</th>
                                </tr>
                                </thead>
                                <tbody id="tableBody">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2020</span>
                </div>
            </div>
        </footer>

    </div>
</div>
<!-- Modal -->
<form id="updateForm" method="POST"></form>
<div class='modal fade' id='exampleModal' tabindex='-1' aria-labelledby='exampleModalLabel' aria-hidden='true'>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="form-group">
                    <label for="exampleInputEmail1" id="modalLabel">Số lượng tồn kho</label>
                    <input type="number" min="0" id="inputNumber" class="form-control" id="exampleInputEmail1"
                           aria-describedby="emailHelp"
                           placeholder="Số lượng">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                <button id="updateButton" type="button" class="btn btn-primary">Lưu</button>
            </div>
        </div>
    </div>
</div>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Bootstrap core JavaScript-->
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Page level plugins -->
<script src="/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="/vendor/datatables/dataTables.bootstrap4.min.js"></script>
<script type="module" src="/vendor/datatables.js"></script>

</body>
</html>
