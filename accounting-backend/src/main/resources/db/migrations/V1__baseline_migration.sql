create TABLE customers (
  id UUID NOT NULL,
   name VARCHAR(255),
   address VARCHAR(255),
   city VARCHAR(255),
   phone VARCHAR(255),
   CONSTRAINT pk_customers PRIMARY KEY (id)
);

create TABLE roles (
  id INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   username VARCHAR(255),
   role VARCHAR(255),
   CONSTRAINT pk_roles PRIMARY KEY (id)
);

create TABLE users (
  username VARCHAR(255) NOT NULL,
   password VARCHAR(255),
   enabled BOOLEAN,
   fullname VARCHAR(255),
   CONSTRAINT pk_users PRIMARY KEY (username)
);

alter table roles add CONSTRAINT uc_b650b56de77e2f2022f4b0970 UNIQUE (username, role);

alter table roles add CONSTRAINT FK_ROLES_ON_USERNAME FOREIGN KEY (username) REFERENCES users (username);