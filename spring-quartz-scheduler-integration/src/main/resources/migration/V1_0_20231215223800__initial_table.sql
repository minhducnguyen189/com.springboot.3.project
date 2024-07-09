CREATE TABLE email_scheduler (
    id uuid NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    subject varchar(255) NOT NULL,
    email_content varchar(5000) NULL,
    email_address varchar(255) NULL,
    days_of_week varchar(100) NULL,
    times_of_day varchar(255) NULL
);

ALTER TABLE email_scheduler
ADD CONSTRAINT email_scheduler_primary_key PRIMARY KEY(id);