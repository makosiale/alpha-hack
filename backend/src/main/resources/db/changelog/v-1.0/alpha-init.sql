CREATE TYPE SEGMENT AS ENUM ('SMALL_BUSINESSES', 'MEDIUM_BUSINESSES', 'LARGE_BUSINESSES');
CREATE TYPE DOC_TRANSACTION_AMOUNT AS ENUM ('NONE', 'LOW', 'MEDIUM', 'HIGH');
CREATE TYPE DOC_URGENCY AS ENUM ('LOW', 'HIGH');
CREATE TYPE DEVICE_TYPE AS ENUM ('MOBILE', 'WEB');
CREATE TYPE BANK_SYSTEM_ISSUES AS ENUM ('NONE', 'SMS', 'PAY_CONTROL', 'QS_TOKEN', 'QS_MOBILE');
CREATE TYPE CLIENT_TYPE_ISSUES AS ENUM ('NONE', 'CONNECTION', 'UNAVAILABLE');
CREATE TYPE BALANCE_OPPORTUNITY AS ENUM ('YES', 'NO');
CREATE TYPE USER_LOCATION AS ENUM ('BUILDING', 'ON_THE_GO');

CREATE TABLE IF NOT EXISTS client (
                                      client_id SERIAL PRIMARY KEY,
                                      mobile_app BOOL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS organization (
                                            organization_id SERIAL PRIMARY KEY,
                                            segment SEGMENT NOT NULL
);

CREATE TABLE IF NOT EXISTS client_role (
                                           client_role_id SERIAL PRIMARY KEY,
                                           client_role_name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS client_organiation_position(
                                                          organization_id INT NOT NULL,
                                                          client_id INT NOT NULL,
                                                          client_role INT REFERENCES client_role (client_role_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS signature(
                                        sign_id SERIAL PRIMARY KEY,
                                        sign_name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS doc_type (
                                        doc_type_id SERIAL PRIMARY KEY,
                                        doc_type_name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS doc (
                                   doc_id SERIAL PRIMARY KEY,
                                   doc_type INT REFERENCES doc_type (doc_type_id) ON DELETE SET NULL,
                                   doc_transaction_amount DOC_TRANSACTION_AMOUNT NOT NULL,
                                   doc_creation_date TIMESTAMP DEFAULT NOW(),
                                   doc_urgency DOC_URGENCY NOT NULL
);

CREATE TABLE doc_organization (
                                  doc_id INT REFERENCES doc (doc_id),
                                  organization_id INT REFERENCES organization (organization_id) ON DELETE SET NULL,
                                  signature INT NULL REFERENCES signature (sign_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS doc_signature (
                                             doc_signature_id SERIAL PRIMARY KEY,
                                             doc_id INT REFERENCES doc (doc_id) ON DELETE SET NULL,
                                             client_id INT REFERENCES client (client_id) ON DELETE SET NULL,
                                             organization_id INT REFERENCES organization (organization_id) ON DELETE SET NULL,
                                             mobile_app BOOL DEFAULT FALSE,
                                             device_type DEVICE_TYPE NOT NULL,
                                             bank_system_issue BANK_SYSTEM_ISSUES NOT NULL,
                                             client_type_issues CLIENT_TYPE_ISSUES NOT NULL,
                                             balance_opportunity BALANCE_OPPORTUNITY NOT NULL,
                                             user_location USER_LOCATION NOT NULL
);