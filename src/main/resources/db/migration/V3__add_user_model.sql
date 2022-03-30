
create table gira_user (
	id varchar(36) not null, 
    create_at timestamp, 
    created_by varchar(36), 
    last_modified_at timestamp, 
    last_modified_by varchar(36), 
    version int4 not null, 
	
    username varchar(100) not null unique,
    password varchar(255) not null,
    display_name varchar(255) not null unique,
    first_name varchar(100),
    last_name varchar(100),
    email varchar(100) not null unique,
    avatar varchar(255),
    status varchar(50) not null,
    department varchar(255),
    major varchar(255),
    hobbies varchar(255),
    facebook varchar(255),
    primary key (id));
    
    
create table gira_group_user (
	group_id varchar(36) not null, 
    user_id varchar(36) not null, 
    primary key (group_id, user_id));
    
alter table if exists gira_group_user 
	add constraint FK_GROUP_ROLE_USER foreign key (user_id) references gira_user(id);
alter table if exists gira_group_user 
	add constraint FK_GROUP_ROLE_GROUP foreign key (group_id) references gira_group(id);