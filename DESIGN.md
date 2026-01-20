## Architecture
The application consists of a React frontend communicating with a Spring Boot REST API.

## Concurrency Strategy
Inventory updates are synchronized at the service layer to ensure atomic decrement operations and prevent overselling under concurrent requests.

## Scalability Consideration
For large-scale traffic, inventory state would be moved to a shared datastore such as Redis or a relational database using atomic operations or row-level locking.

## UX Considerations
The frontend provides clear feedback for loading, success, sold-out, and error states to ensure a smooth user experience under latency or failure.
