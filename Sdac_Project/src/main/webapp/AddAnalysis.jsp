<!DOCTYPE html>
<html>
<head>
    <title>Update Feedback</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>

    <div class="container">
        <h1>Update Product</h1>
        <form method="post" action="AddAnalysis"> 
        <div class="form-group">
                <label for="productID">Product ID:</label>
                <input type="text" class="form-control" id="productID" name="productID">
            </div>
             <div class="form-group">
                <label for="productName">Product Name:</label>
                <input type="text" class="form-control" id="productName" name="productName">
            </div>
            <div class="form-group">
                <label for="performance">Performance:</label>
                <input type="text" class="form-control" id="performance" name="performance">
            </div>
            <div class="form-group">
                <label for="usability">Usability:</label>
                <input type="text" class="form-control" id="usability" name="usability">
            </div>
            <div class="form-group">
                <label for="cost">Cost:</label>
                <input type="text" class="form-control" id="cost" name="cost">
            </div>
            <div class="form-group">
                <label for="environment">Environment:</label>
                <input type="text" class="form-control" id="environment" name="environment">
            </div>
            <div class="form-group">
                <label for="customer_feedback">Customer Feedback:</label>
                <input type="text" class="form-control" id="customer_feedback" name="customer_feedback">
            </div>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" >
            </div>
            
             <div class="form-group">
                <label for="pCatagory">Product Category</label>
                <input type="text" class="form-control"  id="pCatagory" name="pCatagory" >
            </div>
            
             <div class="form-group">
                <label for="date">Date</label>
                <input type="date" class="form-control"  id="date" name="date" >
            </div>
            
            
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
        
    </div>
</body>


</html>
