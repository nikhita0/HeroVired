
// Define an array of customer objects
const customers = [
    { customerID: 1, customerName: 'Akshara', totalBillingAmount: 2500 },
    { customerID: 2, customerName: 'Janu', totalBillingAmount: 3500 },
    { customerID: 3, customerName: 'Vartika', totalBillingAmount: 2800 },
    { customerID: 4, customerName: 'Rahul', totalBillingAmount: 4200 },
  ];
  
  // Function to print customer names and purchase details
  function printCustomerDetails(customers) {
    let foundCustomers = false;
  
    for (const customer of customers) {
      if (customer.totalBillingAmount > 3000) {
        console.log(`Customer Name: ${customer.customerName}`);
        console.log(`Total Billing Amount: Rs. ${customer.totalBillingAmount}`);
        console.log('------------------------');
        foundCustomers = true;
      }
    }
  
    if (!foundCustomers) {
      console.log('No customers found with total billing amount > Rs. 3000.');
    }
  }
  
  // Print customer details for those with total billing amount > 3000
  console.log('Customers with total billing amount > Rs. 3000:');
  printCustomerDetails(customers);
  