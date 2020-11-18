# android-food-order
## Purpose of the project
This add is for staffs at the restaurant to order food for customer.

## App Functions
This app has 4 basic functions to make order: Create Order, Read Order, Update Order, Delete Order. This app also requires staff to login the system to make order. ID and Password are provided by the restaurant.
##### ID: 3756868
##### Password: 123456

## How Things Work
Those order are created and stored in RAM using ArrayList. I created a model for Order and store those orders in the ArrayList. However, I also created some dummy Order add store them in the ArrayList when the app run. Each time the app run, those dummy orders will be created.

## Issues
The notification and Toaste only works with android API below 30. However, the notification only works with the API below 26 (Checked with API 22 - Android 5.0)
