$(document).ready(function(){
    var tabelProduct = $("#tabel-product");
    if (tabelProduct.length){
        tabelProduct.DataTable({
            lengthMenu: [[3, 5, 10, -1], ['Tampilkan 3', 'Tampilkan 5', 'Tampilkan 10', 'Semua']],
            pageLength: 5,
            ajax : {
                url: 'http://localhost:8888/api/product',
                dataSrc: ''
            },
            columns : [
                {data : 'id'},
                {data : 'name'},
                {data : 'price'},
                {data : 'stock'},
                {data : 'category.name'},
                {data : 'createdAt'},
                {data : 'updatedAt'},
                {
                    data: 'id',
                    mRender:function(data, type, row){
                        var str = "";
                        str += "<button class='btn btn-primary updateProduct' data-id='"+data+"' id='update-product'>";
                        str += "Update";
                        str += "</button>&nbsp;";

                        str += "<button class='btn btn-danger deleteProduct' data-id='"+data+"' id='delete-product'>";
                        str += "Delete";
                        str += "</button>";
                        return str;
                    }
                }
            ]
        });
    }
});

$(document).ready(function(){
    var tabelTransaction = $("#tabel-transaction");
    if(tabelTransaction.length){
        tabelTransaction.DataTable({
            lengthMenu:[[3, 5, 10, -1], ['3', '5', '10', 'all']],
            pageLength:5,
            ajax : {
                url: 'http://localhost:8888/api/product',
                method: 'GET',
                dataSrc: ''
            },
            columns : [
                {data:"id"},
                {data:"name"},
                {data:"price"},
                {data:"stock"},
                {
                    data:"id",
                    mRender:function(data, type, row){
                        var str = "";
                        str += "<button data-id='"+data+"' class='btn btn-success buy'>";
                        str += "Buy"
                        str += "</button>";
                        return str;
                    }
                }
            ]
        });
    }
});


$(document).on('click', '.addProduct', function(){
    $(".modalProduct").modal('show');
    $("#formproduct")[0].reset();
});

$(document).on('click', '.updateProduct', function(){
    var productId = $(this).attr("data-id");
    console.log("product id : "+productId);
    $.ajax({
        url: "http://localhost:8888/api/product/by?productId="+productId,
        method: "GET",
        success:function(response){
            console.log("product id : "+response.id);
            console.log("name : "+response.name);
            console.log("category : "+response.category.name);
        }
    });
});