# CS180-Project-5
## Submission
Submitted via Vocareum: Gunwoo Kang

Submitted report: Adrienne Peters
## Welcome.java
This class displays the welcome screen to the market and prompts the user to choose weather to sign-in or sign-up to use the market. From here the user will be taken to the SignIn or SignUp class's run method.

|![WelcomeScreen-removebg-preview](https://user-images.githubusercontent.com/111794312/204955979-c8787389-cc54-4f41-ad21-840874f6c9d7.png)|<img width="200" alt="SignIn_SignUp-removebg-preview" src="https://user-images.githubusercontent.com/111794312/204956049-c82a46c4-98f6-41ae-a974-decd2be47bdd.png">|
|-|-|

## SignIn.java
This class asks the user for their username and password and takes the user to the StoreMenu or CustomerMenu class's run method. An error message is shown if the username/password the user entered was incorrect

|![Login](https://user-images.githubusercontent.com/111794312/204960249-5d40d936-e2f0-40a3-9301-20716f718adf.png)|![LoginError](https://user-images.githubusercontent.com/111794312/204960263-fbc0dc9c-8897-4fe2-95fc-bab557bcdc5e.png)|
|-|-|

## SignUp.java & SignUpInformation.java
This class creates the respective account types by prompting the user for their username, password, and email

|<img width="242" alt="Customer_SellerPrompt-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205740979-42e99977-1fbe-48de-b8d4-ae10e2377d32.png">|<img width="240" alt="CreateAccountPrompt-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205740986-53af0e37-457f-40c6-a074-ce2a382961c0.png">|
|-|-|
|<img width="330" alt="PawwordConfrim2-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205741022-17c23c11-a298-4d01-9402-7c6e6921ed68.png">|<img width="310" alt="PasswordMatch1-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205741025-9cfbebe8-8d10-459b-bbb0-35b88e9440b0.png">|


## StoreMenu.java
If the currentUser is a Seller object then they will be directed to this class which shows actions the store can take: manage store, view statistics, view sales, review account settings, and sign out. After the Seller clicks a button they are taken to the respective class's run method

|![StoreMenu](https://user-images.githubusercontent.com/111794312/204961177-cb71a7f8-7edb-4333-904b-b92786924350.png)|
|-|

## ManageStore.java
If the customer decides to click the manage store button from the StoreMenu class, they will be taken to this class allowing them pick which store they would like to manage

|![ManageStore-removebg-preview](https://user-images.githubusercontent.com/111794312/205095836-3c816ff2-d56c-42e1-bff0-41a2f1805f4e.png)|![ManageStoreDropdown-removebg-preview](https://user-images.githubusercontent.com/111794312/205095848-5fda6132-5ebd-4dee-95e7-d9b57a0e6312.png)|
|-|-|

## StoreOptions.java
After the Seller picks what store they want to manage they are taken to this class where they pick what they would like to do with their store: create a product, modify a product, delete a product, and import and export products
 
 |![StoreOptions-removebg-preview](https://user-images.githubusercontent.com/111794312/205098454-c7d354ff-ee6e-435c-b0ed-bf4773f6d54f.png)|
 |-|
 
 ## CreateProduct.java
 This class allows for Sellers to create new products to add to their store
 
|![CreateProduct-removebg-preview](https://user-images.githubusercontent.com/111794312/205126549-4a2138b7-cb5d-4926-b54a-f4e5ce89a71e.png)|![CreateProductImagePrompt-removebg-preview](https://user-images.githubusercontent.com/111794312/205126564-4b750337-f251-437f-bf4a-0b86fc27a5ae.png)|
|-|-|
|__If user wants to add an image__||
|![IfImagemdpi](https://user-images.githubusercontent.com/111794312/205128049-c86b92ba-4e14-4b42-b257-7f1b34bfbf08.png)|<img width="313" alt="ImageToString-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205128066-ae692f27-8235-4550-9758-d1685daa0342.png">|
|__If user does not want to add an image__||
|![NoImageToString-removebg-preview](https://user-images.githubusercontent.com/111794312/205128128-d5eaa943-51f5-4a4a-818b-f0b17cf43610.png)||

## ModifyProduct.java
This class allows the Seller to modify attributes of products: name, description, quantity, price and photo

|<img width="347" alt="ModifyProduct-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205133767-ca636f52-7912-4a96-8d48-498d42a6d014.png">|<img width="355" alt="ModifyProductDropDown-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205133795-83ef3557-90e4-4d87-b8f5-39284cc3ab9f.png">|
|-|-|
|<img width="328" alt="ModifyAttribute-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205133829-02efff44-1ae0-459e-97e4-2636a1c8c43d.png">|<img width="325" alt="ModifyAttributeDropDown-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205133851-50d337b2-7f03-4493-ba35-ab2b4309a322.png">|
|__If user wants to modify the name of a product__||
|<img width="349" alt="NewName-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205134132-aa51297f-0d21-4d71-a2c3-c873186f268d.png">|![NameConfirm-removebg-preview](https://user-images.githubusercontent.com/111794312/205134144-a9fbad3a-dec5-47ed-bcbb-3a0221f103d3.png)|
|__If user wants to modify the description of a product__||
|<img width="347" alt="NewDescription-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205159896-5e4090a7-3f5e-4bef-b093-cede585c0f5e.png">|![NewDescriptionConfirm-removebg-preview](https://user-images.githubusercontent.com/111794312/205159929-fe2f4714-4c62-42e2-abef-324981f0f60d.png)|
|__If user wants to modify the quantity of a product__||
|<img width="347" alt="NewQuantity-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205159795-86576130-2471-41df-9e53-d20476c01e30.png">|![NewQuantityConfirm-removebg-preview](https://user-images.githubusercontent.com/111794312/205159824-17b82daf-564a-441c-b56f-ba116610dcf3.png)|
|__If user wants to modify the price of a product__||
|<img width="348" alt="NewPricePrompt-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205159994-f0946450-0d62-4914-a2d2-9f32185cd9e2.png">|![Screen_Shot_2022-12-01_at_4 06 38_PM-removebg-preview](https://user-images.githubusercontent.com/111794312/205160041-61de51e4-d9d8-495e-9385-2f2f2cf5bfa1.png)|
|__If user wants to modify the photo of a product__||
|![IfImagemdpi](https://user-images.githubusercontent.com/111794312/205160136-6a70dfe2-1447-45db-9ebd-890ed12137b2.png)|<img width="377" alt="Screen_Shot_2022-12-01_at_4 07 51_PM-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205160176-7ce8c01a-1bfa-49c6-9152-2251e4881195.png">|

## DeleteProduct.java
This class allows the seller to delete products from their store

|![Screen_Shot_2022-12-01_at_4 22 46_PM-removebg-preview](https://user-images.githubusercontent.com/111794312/205183442-9ec1b6c1-99e8-4585-bc35-53a03bfa6b43.png)|<img width="346" alt="DeleteConfirm-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205161562-0d005b49-0b11-4f80-9efc-5eecdd48d38f.png">|
|-|-|

## ImportProduct.java
This class allows the seller to import products to their store in the form of a CSV file. If there is a path to an image in the CSV file, the coorisponding product will have that image added to it

|__CSV File__|
|-|
|<img width="316" alt="CSVFile" src="https://user-images.githubusercontent.com/111794312/205187991-b6c6d912-9feb-4999-96fb-f4ea922170f2.png">|

|![IfImagemdpi](https://user-images.githubusercontent.com/111794312/205160136-6a70dfe2-1447-45db-9ebd-890ed12137b2.png)||
|-|-|
|![AddedProdutc](https://user-images.githubusercontent.com/111794312/205188032-85420d6b-d40b-4a89-8290-4bd278da837d.png)|<img width="315" alt="NoImgToString" src="https://user-images.githubusercontent.com/111794312/205188036-cab4ff46-4b01-4890-9429-845d97b997b3.png">|

## ExportProduct.java
This class allows the seller to import products to their store in the form of a CSV file. Like the ImportProduct, this class exports the nessecary image paths to the CSV

|![IfImagemdpi](https://user-images.githubusercontent.com/111794312/205160136-6a70dfe2-1447-45db-9ebd-890ed12137b2.png)||
|-|-|
|![Exportmdpi](https://user-images.githubusercontent.com/111794312/205201764-e5cf2970-0a0e-4bdb-99e5-0940f12fcdf5.png)|<img width="315" alt="NoImgToString" src="https://user-images.githubusercontent.com/111794312/205188036-cab4ff46-4b01-4890-9429-845d97b997b3.png">|

|__CSV File__|
|-|
|<img width="316" alt="CSVFile" src="https://user-images.githubusercontent.com/111794312/205187991-b6c6d912-9feb-4999-96fb-f4ea922170f2.png">|

## SellerStatistics.java
This class shows the seller their statistics, either sorted or unsorted. The output shows the customers' names and how many products they have purchased from the store and then how many of each product have been sold

|<img width="343" alt="StatsPrompt-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205735793-e3c2de00-2ae4-41f6-b50c-e5ada4bd2b58.png">|<img width="285" alt="StatsOutput-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205735802-e9e298ca-f63d-49b1-b0b1-26f48341d70f.png">|
|-|-|

## SeeSales.java
This class shows the seller their sales and total revenue from a selected store

|<img width="360" alt="SeeSalesPrompt-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205736664-a01076d1-efd6-4b19-a8be-b0ee4bc0122e.png">|<img width="331" alt="SeeSalesOutput-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205736674-00121eb1-51d3-4315-830b-9a078e337dcc.png">|
|-|-|

## CustomerMenu.java
If the currentUser is a Customer object then they will be directed to this class which shows actions the customer can take: view marketplace, view cart and history, view statistics, review account settings, and sign out. After the Seller clicks a button they are taken to the respective class's run method

|<img width="350" alt="Screen_Shot_2022-12-08_at_1 15 33_AM-removebg-preview" src="https://user-images.githubusercontent.com/111794312/206371941-49ad983f-02b2-41e8-8d85-48c4ce94124a.png">|
|-|

## ViewMarketplace.java
This class allows for customers to view all the products available for purchase. The customer has to option to search by product name and also by store name

|<img width="296" alt="Screen_Shot_2022-12-08_at_1 18 00_AM-removebg-preview" src="https://user-images.githubusercontent.com/111794312/206372525-2aa85bcf-cf36-4c1e-bfea-9460d143bef9.png">|<img width="301" alt="Screen_Shot_2022-12-08_at_1 18 05_AM-removebg-preview" src="https://user-images.githubusercontent.com/111794312/206372536-8f83af24-1995-41f6-871e-d9d2ce135835.png">|
|-|-|

|__Search Products Named Apples__|__Whole Marketplace__|__After filtered__|
|-|-|-|
|<img width="275" alt="Screen_Shot_2022-12-08_at_1 55 03_PM-removebg-preview" src="https://user-images.githubusercontent.com/111794312/206544006-6f9fd589-b39c-4491-a5a8-b93dc61e0649.png">|<img width="275" alt="Screen_Shot_2022-12-08_at_1 54 50_PM-removebg-preview" src="https://user-images.githubusercontent.com/111794312/206544169-04e8a46b-f72c-4a89-bd96-82dc41e62331.png">|<img width="302" alt="Screen_Shot_2022-12-08_at_1 55 22_PM-removebg-preview" src="https://user-images.githubusercontent.com/111794312/206544210-9b3ee3f8-2a6b-418b-9bc9-9b3a2d95d769.png">|

|__Search Products from Shop1__|__Whole Marketplace__|__After Filtered__|
|-|-|-|
|<img width="309" alt="Screen_Shot_2022-12-08_at_1 58 09_PM-removebg-preview" src="https://user-images.githubusercontent.com/111794312/206544497-1d067eb5-1435-474c-a515-9e8bab60e9a8.png">|<img width="275" alt="Screen_Shot_2022-12-08_at_1 54 50_PM-removebg-preview" src="https://user-images.githubusercontent.com/111794312/206544526-e2ee55da-9663-4cf7-81b4-85221ecfa3eb.png">|<img width="301" alt="Screen_Shot_2022-12-08_at_1 18 05_AM-removebg-preview-removebg-preview" src="https://user-images.githubusercontent.com/111794312/206544539-5fc9ddce-2b5b-4bce-bdd8-4536bbbc61d1.png">|


## ShowProductPane.java
This class is a JFrame formatted to look like a JOptionPane with an added "Add to Cart" button
|<img width="345" alt="Screen_Shot_2022-12-08_at_1 41 43_AM-removebg-preview" src="https://user-images.githubusercontent.com/111794312/206376689-1cf041be-cb99-485b-ad2c-296e279135e5.png">|
|-|

## CartAndPurchaseHistory.java
This class allows the customer to pick to view their cart or their purchase history. If the cutomer picks to view their cart the user is shown the contents of their cart, and if the customer picks to export their purchase history they pick which file they would like their purchase history to be saved to

|<img width="335" alt="Screen_Shot_2022-12-08_at_1 27 58_AM-removebg-preview" src="https://user-images.githubusercontent.com/111794312/206374412-672c5353-6007-44fd-9735-f22392d2e861.png">| |
|-|-|
|__View Cart__|__Buy__|
|<img width="303" alt="Screen_Shot_2022-12-08_at_1 28 07_AM-removebg-preview" src="https://user-images.githubusercontent.com/111794312/206374481-a90bc12c-514c-4469-aa65-6de1f4e3fa0e.png">|<img width="352" alt="Screen_Shot_2022-12-08_at_1 28 13_AM-removebg-preview" src="https://user-images.githubusercontent.com/111794312/206374565-9d33859a-5e35-4444-8d79-28153219c2e0.png">|
|__Purchase History__|__.txt File__|
|![IfImagemdpi](https://user-images.githubusercontent.com/111794312/205160136-6a70dfe2-1447-45db-9ebd-890ed12137b2.png)|<img width="580" alt="Screen Shot 2022-12-08 at 1 32 56 AM" src="https://user-images.githubusercontent.com/111794312/206375093-c848ffb2-d121-4534-bea5-3ebe4fbd83a5.png">|

## CustomerStatistics.java
Shows the customer their statistics, either sorted or unsorted

|<img width="284" alt="Screen_Shot_2022-12-08_at_1 38 00_AM-removebg-preview" src="https://user-images.githubusercontent.com/111794312/206376070-2f778c16-ae70-45e4-93e4-8b563b2d415d.png">|
|-|

## AccountSettings.java
This class allows for changes to the username, password, or email of a user, and the user has the option to delete their account

|<img width="339" alt="AccountSettings-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205738474-6a6cc0d0-262e-4f69-8e3e-ed8397e2df8f.png">|__Success__|__Failure__|
|-|-|-|
|<img width="340" alt="NewUsernamePrompt-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205738518-c33440d4-4dee-48f7-a601-0f1c00e900b8.png">|<img width="324" alt="NewUsernameConfirm-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205738528-1d27d70f-6fe0-4242-a43c-ef4c29c5af11.png">|<img width="337" alt="UsernameMatch-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205738542-1a9b63f9-463f-4ae1-890c-51dafb9fb91e.png">|
|<img width="335" alt="NewEmailPrompt-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205738996-eb85ca90-e3f5-4a75-bab7-bd035ee3325b.png">|<img width="322" alt="NewEmailConfirm-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205739004-1a439d8b-1fb4-4cb4-8c5c-6554022c0f3c.png">|<img width="333" alt="EmailMatch-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205739012-58084bb9-eed7-41a5-a6ba-fce2b0f8b648.png">|
|<img width="336" alt="NewPasswordPrompt-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205739200-b0361f83-ddc8-42f5-8265-97bf959ff98c.png">|<img width="335" alt="PasswordConfirm-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205739210-2e08b0f6-8ac9-4759-b46c-12e62e086383.png">|<img width="323" alt="PasswordMatch-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205739217-3937e5e3-7289-4f65-8916-b4a3a1822a0d.png">|
|<img width="362" alt="DeleteConfirm-removebg-preview (1)" src="https://user-images.githubusercontent.com/111794312/205739269-d9b3fc71-3dfa-4e3f-b75a-d88a712ac11e.png">|<img width="327" alt="DeleteSuccess-removebg-preview" src="https://user-images.githubusercontent.com/111794312/205739282-2fc506c5-6cba-40bf-92f4-a420b50d3204.png">| |



