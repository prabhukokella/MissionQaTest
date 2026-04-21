# QA Automation Assignment

1. I1mplemented ThreadLocal WebDriver and removed shared user-data-dir. Each test now runs in an isolated browser instance, ensuring thread safety and stable parallel execution.

2. Refactored config loading to use classpath (getResourceAsStream) and added support for environment variables, making it CI/CD compatible and environment-agnostic.

3. Introduced DriverProvider interface and implemented a flexible driver factory supporting multiple browsers (Chrome, Firefox, Edge) without modifying existing code.

4. Moved API base URL to config.properties, enabling execution across different environments (dev/qa/prod) without code changes.

5. ensured all service classes encapsulate meaningful business logic, improving clarity and maintainability.

6. Implemented interfaces like: DriverProvider,APIExecutor,ConfigProvider This ensures loose coupling and better extensibility.

7. Refactored to constructor-based dependency injection, ensuring components depend on abstractions rather than concrete classes.

8. Replaced with stable locators

9. Moved all business logic (totals, tax calculations) to the Service layer, keeping Page Objects strictly for UI interactions.

10. Refactored method to dynamically fetch all pages using total_pages, ensuring method behavior matches its name.

11. Extended API client to support: GET,POST,PUT,DELETE

12. Improved User model
13. Removed unused and duplicate code (e.g., redundant driver classes), ensuring a clean and maintainable codebase.
