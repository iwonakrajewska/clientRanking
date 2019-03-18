# Problem Description

*Comb as You Are* have decided to ditch their old salon software provider
and upgrade to *Phorest* to avail of its best in class client retention tools.
They're exicted to finally offer their clients the opportunity to book online.

They've exported their clients appointment data from their old provider and
would like to email their top 50 most loyal clients of the past year with the news
that they can now book their next appointment online.

# Problem Spec

The exported data is split across 4 files.

* clients.csv
* appointments.csv
* services.csv
* purchases.csv

Each client has many appointments and are related through a `client_id` property on the appointment
Each appointment has many services and are related through an `appointment_id` property on the service
Each appointments has 0 or many purchases and are related through an `appointment_id` property on the purchase
Services and purchases have an associated number of loyalty points defined as a property
Clients have a boolean banned property defined on the client

In a programming language of your choice write some code to parse the exported data and
list the top 50 clients that have accumulated the most loyalty points since January 1st 2018.
Please exclude any banned clients.

# Solution

Do as much as you can in the time you have available to you. Please still submit your solution even if it's not complete. You can always add a few notes stating 
what's missing and/or how you would improve the solution if you had more time. 

## Testing
We would prefer to see a partial solution which is accompanied by tests, than a fully working solution without tests.

# Submission  

Please submit your solution in the form of a link to a public source control repository which contains your code e.g Github, Gitlab etc