# Deloitte_Clothes

## Architecture & Technology Used:- 
## MVVM with Live Data
## ROOM DB for Local Storage
## Retrofit for Network Call
## DataBinding

## APP Description

## Note-
1- All images are Dummy

2- I have covered and completed all stories mentioned in task sheet.

## Splash Screen

1-When Click on App Icon -> It will show first splash Screen 

## Categorty Screen

1-After Splash Screen - > Category Screen Comes

## Filtered Product List 

1-when clicked on Specific Category- > moved to product List page where all items of choosen category

2-in Product List Page You can seen product and can mark as wishlist, wishlisted item shown in wishlist screen

## Product Details Screen

1-when you clicked item from product list -> Move to product Details screen

2-in Product detail page you can add product to cart (if and only if item is avaliable).added cart item will show in Cart screen, All cart item added into ROOM DB(Because we do not have the api for getting wishlist List.)

3-Stock out product can not be added in cart.

4-Add Item by clicked on wishlist icon on detail page, all wishlisted item added into ROOM DB(Because we do not have the api for getting wishlist List.)

## Wishlist Screen

1- in Wishlist screen all wishlisted items will show, if there is no items in wishlist it will show empty wishlist screen

2- on Click of bin wishlist item will remove from list and DB.

3- From Header you can direct jump into cart

## Cart Screen

1- in Cart screen all cart items will show, if there is no items in cart it will show empty cart screen

2- on Click of bin wishlist item will remove from list and DB & server.

3- From Header you can direct jump into wishlist


## Suggestion 

1-There should an api for wishlist listing,Because if any item price got changed, we will get updated wishllist everytime.

2-There should be an api for wishlist item remove,If app is multi login we can sync wishlist to all devices

3-There should be an api for getting cart listing, Because if any item price got changed, we will get updated wishllist everytime.

