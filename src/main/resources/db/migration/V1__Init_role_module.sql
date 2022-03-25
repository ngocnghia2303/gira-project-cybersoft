
create table gira_group (
	id varchar(36) not null, 
    create_at timestamp, 
    created_by varchar(36), 
    last_modified_at timestamp, 
    last_modified_by varchar(36), 
    version int4 not null, 
    code varchar(100), 
    description varchar(255), 
    primary key (id));
    
create table gira_group_role (
	group_id varchar(36) not null, 
    role_id varchar(36) not null, 
    primary key (group_id, role_id));
    
create table gira_role (
	id varchar(36) not null,
    create_at timestamp, 
    created_by varchar(36), 
    last_modified_at timestamp, 
    last_modified_by varchar(36), 
    version int4 not null, 
    code varchar(5), 
    description varchar(255), 
    primary key (id));

alter table 
if 
	exists gira_group_role add constraint FK_GROUP_ROLE_ROLE foreign key (role_id) references gira_role;
alter table 
if
	exists gira_group_role add constraint FK_GROUP_ROLE_GROUP foreign key (group_id) references gira_group;