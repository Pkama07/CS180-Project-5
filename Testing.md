__These tests have been tested for if the user closed the GUI and has passed__

# Test 1: User log in

### Steps:

1. The user launches the marketplace

2. The user clicks the “Ok” button on the welcome screen

3. The user clicks the “Sign in” button on the next screen

4. The user enters their username and password into the respective text fields

5. The user clicks the “Login” button

### Expected Result:

The marketplace verifies their username and password if it is correct and loads the respective menu. If the username and password don’t match, an error message is shown.

## Test Status: __Passed__

# Test 2: User sign up

### Steps:

1. The user launches the marketplace

2. The user clicks the “Ok” button on the welcome screen

3. The user clicks the “Sign up” button on the next screen

4. The user clicks either the “I’m a customer” or the “I’m a seller” button

5. The user enters their email, username, and password into the respective text fields

6. The user clicks the “Create account” button

7. The user confirms their password and clicks the “Ok” button

### Expected Result:

The marketplace makes the respective accounts. If the user clicked the “I’m a seller” button, the user is prompted for their store name and the marketplace makes a store under that account

## Test Status: __Passed__

# Test 3: Welcome screen cancel and close

### Steps:

1. The user clicks the “Cancel” button or closes the frame when the welcome screen greets them

### Expected Result:

The marketplace closes, and the program stops runnings

## Test Status: __Passed__

# Test 4: User log in / sign up back button

### Steps: 

1. The user progresses from the welcome screen to the sign in / sign up screen

2. The user clicks either the “Sign in” or “Sign up” buttons

3. The user progresses to the respective screens

4. The user clicks the “Back” button

### Expected Result: 

The marketplace goes back to the welcome screen

## Test Status: __Passed__

# Test 5: Seller dashboard

### Steps: 

1. The user completes test 1 or test 2 using an account linked to a seller

### Expected Result: 

The user is met with a dashboard with the following buttons: “Manage Stores” , “Create New Store” , “View Statistics”, “View Sales”, “Account Settings”, and “Sign Out”

## Test Status: __Passed__

# Test 6: Customer dashboard

### Steps: 

1. The user completes test 1 or test 2 using an account linked to a customer

### Expected Result: 

The user is met with a dashboard with the following buttons: “View Marketplace”, “Cart and History”, “View Statistics”, "Account Settings", and “Sign Out”

## Test Status: __Passed__

# Test 7: Manage Stores

### Steps: 

1. Once the seller completes test 5, the seller clicks the “Manage Stores” button

2. The seller selects which store they would like to manage from the drop-down menu and clicks the “Ok” button

### Expected Result:

The seller is met with a screen that has the following buttons: “Create Product”, “Modify Product”, “Delete Product”, “Import Product” and “Export Product”

## Test Status: __Passed__

# Test 8: Create Product

### Steps: 

1. Once the seller completes test 7, they click the “Create Product” button

2. The seller fills of the text fields labeled “Name”, “Description”, “Quantity”, and “Pirce”, inputting a number in the “Quantity” and “Pirce” fields

3. The seller clicks the “Create” button

4. The seller is promoted if they would like to add an image to the new product. If they select the “Yes” button, they will be prompted with a file chooser, and if not, they will continue onto the next step.

5. The marketplace tells the seller that a new product has been added to their store with the following information.

6. The marketplace shows the seller a screen with the information they just entered formatted and with the picture they chose or with the default picture.

### Expected Result:

The seller’s store has the product added to it

## Test Status: __Passed__

# Test 9: Modify Product

### Steps:

1. Once the seller completes test 7, they click the “Modify Product” button

2. The seller picks the product they would like to modify from the drop down menu and clicks the “Ok” button

3. The seller picks the attribute they would like to modify from the drop down menu and clicks the “Ok” button

4. The seller types in the new value they would like for the selected attribute and clicks the “Change” button

5. The seller clicks the “Yes” button on the confirmation screen

### Expected Result:

The product’s attribute changes

## Test Status: ___Passed___

# Test 10: Delete Product

### Steps:

1. After the seller completes test 7, they click the “Delete Product” button

2. The seller picks the product they would like to delete from the drop-down menu and clicks the “Ok” button

3. The seller clicks the “Yes” button on the confirmation screen

### Expected Result:

The product is removed from the store

## Test Status: ___Passed___

# Test 11: Import Product
### Steps:
1. After the seller completes test 7, they click the “Import Product” button

2. The seller selects the CSV file they would like to import their products from and clicks the “Open” button

3. The seller clicks the “Ok” button on each of the following screens that show what products have been added to the store

### Expected Result:

The imported products are added to the store

## Test Status: ___Passed___

# Test 12: Export Product
### Steps:
1. After the seller completes test 7, they click the “Export Product” button

2. The seller selects the CSV file they would like to export their products from and clicks the “Save” button

3. The seller clicks the “Ok” button on each of the following screens that show what products have been saved to the file

### Expected Result:

The products’ information is written to the desired file

## Test Status: ___Passed___

# Test 13: Create New Store
### Steps:
1. The seller completes test 5 

2. The seller clicks the “Create New Store” button

3. The seller enters their desired store name and clicks the “Create” button

### Expected Result:

The new store is associated with the seller’s account

## Test Status: ___Passed___

# Test 14: View Seller Statistics
### Steps:
1. The seller completes test 5 

2. The seller clicks the “View Statistics” button

3. The seller clicks the “Yes” button on the prompt if they want their statistics sorted and “No” if they do not

### Expected Result:

The seller is shown their stores’ statistics

## Test Status: ___Passed___

# Test 15: View Sales
### Steps:
1. The seller completes test 5 

2. The seller clicks the “View Sales” button

3. The seller selects which store they want to see the sales from and then clicks the “Ok” button

### Expected Result:

The seller is shown the total sales and total revenue from the selected store

## Test Status: ___Passed___

# Test 16: View Marketplace
### Steps:
1. The customer completes test 6

2. The customer clicks the “View Marketplace” button

3. The customer clicks on the product they wish to view

4. The customer clicks the “Add to cart” button

5. The customer selects how many of the product they want to add to their cart

6. The customer clicks the “Ok” button

### Expected Result:

The product is added to the customer’s cart

## Test Status: ___Passed___

# Test 17: View Marketplace Search Product Name
### Steps:
1. The customer completes test 6

2. The customer clicks the “View Marketplace” button

3. The customer types in the text field left of the “Search Product Name” button the name they would like to search for

4. The customer clicks on the “Search Product Name” button

### Expected Result:

The customer is shown a marketplace with only products which names contain the text the customer entered in the text field

## Test Status: ___Passed___

# Test 18: View Marketplace Search Store Name
### Steps:
1. The customer completes test 6

2. The customer clicks the “View Marketplace” button

3. The customer types in the text field left of the “Search Store Name” button the name they would like to search for

4. The customer clicks on the “Search Store Name” button

### Expected Result:

The customer is shown a marketplace with only products which store names contain the text the customer entered in the text field

## Test Status: ___Passed___

# Test 19: View Cart and Buy
### Steps:
1. The customer completes test 16 

2. The customer redirects themselves back to the menu by clicking the “Back” buttons

3. The customer clicks the “Cart and History” button

4. The customer clicks the “View Cart” button

5. The customer is shown an over view of their cart

6. The user clicks the “Buy” button

7. The user clicks the “Ok” button on the following screen

### Expected Result:

The products have been added to the customers record of products bought

## Test Status: ___Passed___

# Test 20: Export Purchase History
### Steps:
1. The customer completes test 6

2. The customer clicks the “Cart and History” button

4. The customer clicks the “Export Purchase History” button

5. The customer selects the text file they would like the information to be saved to 

6. The customer clicks the “Save” button

### Expected Result:

The selected text file has the contents of the customer’s purchase history

## Test Status: ___Passed___

# Test 21: View Customer Statistics
### Steps:
1. The customer completes test 6

2. The customer clicks the “View Statistics” button

3. The customer clicks the “Yes” button on the prompt if they want their statistics sorted and “No” if they do not

### Expected Result:

The customer is shown their buying statistics

## Test Status: ___Passed___

# Test 22: Account Settings, Change Email, Username, or Password
### Steps:
1. The user (can be a seller or customer) completes test 5/6

2. The user clicks the “Account Settings” button

3. The user clicks either the “Change username”, “Change email”, “Change password” button

4. The user types in the “New username”, “New email”, “New password” text field and confirms the username, email, or password

5. The user clicks the “Change” button

6. The user clicks the “Ok” button on the following screen

### Expected Result:

The user’s username, email, or password changes

## Test Status: ___Passed___

# Test 23: Account Settings, Delete Account
### Steps:
1. The user (can be a seller or customer) completes test 5/6

2. The user clicks the “Account Settings” button

3. The user clicks the “Delete Account” button

4. The user clicks the “Yes” button on the conformation screen

5. The farewell screen shows

### Expected Result:

The user’s account is removed

## Test Status: ___Passed___

# Test 24: Sign Out
### Steps:
1. The user (can be a seller or a customer) completes test 5/6

2. The user clicks the “Sign Out” button

### Expected Result:

The farewell screen shows

## Test Status: ___Passed___

## Test 25: More than one user opperating the application at the same time
### Steps:
1. Run the command javac Server.java followed by java Server
2. Run the command javac Main.java followed by java Main
3. The client is connected to the server
4. Complete step 2 again as many times as desired
### Expected Result:
More than one user can access the application at once

## Test Status: ___Passed___
