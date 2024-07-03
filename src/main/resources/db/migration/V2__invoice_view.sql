CREATE VIEW invoice_view AS
SELECT i.* , c.fullname
FROM client c JOIN invoice i ON c.id = i.client_id;