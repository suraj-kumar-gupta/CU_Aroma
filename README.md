# CU Aroma
CUAroma is an android application which is designed using Android Studio. There are two applications one for the client and one for the vendor. The vendor application uses a simple user Interface to add food item to menu and to view the orders which have been received and in the same way the client application has an interface in which aclient can sign in and view the menu and then finally place the order. Both the application shares the same Database. Before using the application, the user needs to authenticate himself/herself using email-id and password and further access the features of the application.
It basically is designed to remove the system of pamphlet and paper menus so that the technology in the campus as being an engineering college goes to the next level where there is the concept of digitization and cashless shopping. Students in the University will be able to order food from where they are. They need not stand in long queues to wait for their order and the rush during the meal hours will be minimized a lot.

# Below are the classes of CUAroma explained in detail –
# CUAroma CLIENT APP –
 Splash – This is an activity which is the first screen that appears for a particular interval of time when the activity is launched.

 SignupActivity – It authenticates the user in the database using the email and password.

 LoginActivity – It allows the authenticated user to sign in to the application using email and password enabling the user to use the features of the application.

 ResetPasswordActivity – Here the user needs to provide his/her email address where a link is sent so that in future it becomes easy for the user to reset his/her password if he/she forgets it.

 ProfileActivity – Here the logged in user needs to update his information like name, address, contact etc. so that it becomes easy to identify the customer easily while making transactions.

 MenuActivity – This is the main part of the app where all the items uploaded by the vendorare displayed in a particular manner.

 SingleFoodActivity – This is the activity which contains the detail of a single item in a card layout.

 OrderConfirmation – This is the activity which comes after the user has placed an order.

 UserInformation – This is a class which consists the details of the customer like his name, address and contact number.

 Food – It is a class where the details of the food item like its name, price, description and image is stored and fetched.

 AboutUs – It is an activity which shows in the details of the app developers at present.

# CUAroma VENDOR APP –
 Splash – This is an activity which is the first screen that appears for a particular interval of time when the activity is launched.

 VendorSignup – It authenticates the vendor in the database using the email and password.

 VendorLogin – It allows the authenticated vendor to sign in to the application using email and password enabling the user to use the features of the application.

 MainActivity – It is an activity which contains a layout which contains all the actions that can be performed like addFood, openOrders, logout etc.

 ResetPasswordActivity – Here the user needs to provide his/her email address where a link is sent so that in future it becomes easy for the user to reset his/her password if he/she forgets it.

 AddFood – This allows the vendor to upload items to the database which will be visible to the customers while viewing the menu.

 OpenOrders – This allows the vendor to know about the customer who has ordered the food (if any) and through this they can contact the customer and get the item delivered.

 OrderConfirmation – This allows the vendor to make sure that the order is prepared and now he/she can contact the customer to initiate the delivery task.

 Orders – It is a class which contains the information of the order like customer name, contact number and item which the customer has ordered and in what quantity he/she has ordered.

# FIREBASE

A fully managed platform for building iOS, Android, and web apps that provides automatic data synchronization, authentication services, messaging, file storage, analytics, and more. The Firebase Real-time Database is a cloudhosted database. Data is stored as JSON and synchronized in real-time to every connected client.When you build cross-platform apps with our iOS, Android, and JavaScript SDKs, all of your clients share one Real-time Database instance and automatically receive updates with the newest
data.
We have two applications one for the client and one for the vendor both sharing the same database.
This project uses the following components of the Firebase –
1) REALTIME DATABASE - Firebase provides a real-time database and backend as a service. The service provides application developers an API that allows application developers and API that allows application data to be synchronized across clients and
stored on Firebase’s cloud.

2) FIREBASE AUTH - Firebase Auth is a service that can authenticate users using only client-side code. It supports social login providers Facebook, Twitter and Google. User can sign up for the application through the createNewUserWithEmailAndPassword() method
provided by the Firebase Auth object and in the same way the user can login into the application through the signInUserWithEmailAndPassword.

3) FIREBASE STORAGE - Firebase storage provides secure file uploads and downloads for firebase apps, regardless of network quality. Here this database is used to upload the item in the vendor application and this same uploaded item is visible in a proper list view to the users.

-------------------------------------------------------------THANKYOU-----------------------------------------------------------
