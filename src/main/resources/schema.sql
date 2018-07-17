-- 创建员工表
CREATE TABLE IF NOT EXISTS `yhcc_employee`(

  `id` VARCHAR (50) NOT NULL,
  `num` VARCHAR (100),
  `name` VARCHAR (100),
  `phone` VARCHAR (100),
  `email` VARCHAR (100),
  `user_name` VARCHAR (100),
  `password` VARCHAR (100),
  `state` TINYINT (1),
  `type` int(11),
  `org_id` VARCHAR (100),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (50),
  `modify_user` VARCHAR (50),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建部门表
CREATE TABLE IF NOT EXISTS `yhcc_department`(

  `id` VARCHAR (50) NOT NULL,
  `num` VARCHAR (100),
  `name` VARCHAR (100),
  `company_id` VARCHAR (50),
  `parent_id` VARCHAR (50),
  `type` int(11),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (50),
  `modify_user` VARCHAR (50),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建部门员工关系表
CREATE TABLE IF NOT EXISTS `yhcc_employee_department_relation`(

  `id` VARCHAR (50) NOT NULL,
  `employee_id` VARCHAR (50),
  `department_id` VARCHAR (50),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (50),
  `modify_user` VARCHAR (50),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建员工角色关系表
CREATE TABLE IF NOT EXISTS `yhcc_employee_role_relation`(

  `id` VARCHAR (50) NOT NULL,
  `employee_id` VARCHAR (50),
  `role_id` VARCHAR (50),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (50),
  `modify_user` VARCHAR (50),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建角色表
CREATE TABLE IF NOT EXISTS `yhcc_role`(

  `id` VARCHAR (50) NOT NULL,
  `name` VARCHAR (100),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (50),
  `modify_user` VARCHAR (50),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建菜单表
CREATE TABLE IF NOT EXISTS `yhcc_menu`(

  `id` VARCHAR (50) NOT NULL,
  `name` VARCHAR (100),
  `is_root` TINYINT (1),
  `parent_id` VARCHAR (50),
  `menu_type` VARCHAR (50),
  `url` VARCHAR (50),
  `sort` int(11),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (50),
  `modify_user` VARCHAR (50),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建角色菜单关系表
CREATE TABLE IF NOT EXISTS `yhcc_role_menu_relation`(

  `id` VARCHAR (50) NOT NULL,
  `role_id` VARCHAR (50),
  `menu_id` VARCHAR (50),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (50),
  `modify_user` VARCHAR (50),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建Token表
CREATE TABLE IF NOT EXISTS `yhcc_token`(

  `id` VARCHAR (50) NOT NULL,
  `access_token` VARCHAR (50),
  `refresh_token` VARCHAR (50),
  `employee_id` VARCHAR (50),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (50),
  `modify_user` VARCHAR (50),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建区域表
CREATE TABLE IF NOT EXISTS `yhcc_area`(

  `id` VARCHAR (50) NOT NULL,
  `num` VARCHAR (50),
  `name` VARCHAR (100),
  `level` int(11),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建项目负责人组员表
CREATE TABLE IF NOT EXISTS `yhcc_employee_project_relation_team`(

  `id` VARCHAR (50) NOT NULL,
  `employee_id` VARCHAR (50),
  `project_id` VARCHAR (50),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建项目表
CREATE TABLE IF NOT EXISTS `yhcc_project`(

  `id` VARCHAR (50) NOT NULL,
  `num` VARCHAR (50),
  `area_id` VARCHAR (50),
  `company_id` VARCHAR (50),
  `name` VARCHAR (50),
  `project_head_id` VARCHAR (50),
  `project_audit_id` VARCHAR (50),
  `state` TINYINT (1),
  `acreage` decimal(18,2),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建公司表
CREATE TABLE IF NOT EXISTS `yhcc_company`(

  `id` VARCHAR (50) NOT NULL,
  `num` VARCHAR (50),
  `name` VARCHAR (50),
  `city_id` VARCHAR (50),
  `parent_id` VARCHAR (50),
  `type` int(11),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建项目图片表
CREATE TABLE IF NOT EXISTS `yhcc_project_image`(

  `id` VARCHAR (50) NOT NULL,
  `project_id` VARCHAR (50),
  `url` VARCHAR (500),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建员工项目数据权限表
CREATE TABLE IF NOT EXISTS `yhcc_employee_project_data`(

  `id` VARCHAR (50) NOT NULL,
  `project_id` VARCHAR (50),
  `employee_id` VARCHAR (500),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建城市表(直辖市+省)
CREATE TABLE IF NOT EXISTS `yhcc_city`(

  `id` VARCHAR (50) NOT NULL,
  `num` VARCHAR (50),
  `name` VARCHAR (500),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建指标表
CREATE TABLE IF NOT EXISTS `yhcc_target`(

  `id` VARCHAR (50) NOT NULL,
  `num` VARCHAR (50),
  `name` VARCHAR (500),
  `show_name` VARCHAR (500),
  `state` TINYINT (1),
  `target_type` int(11),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建业态表
CREATE TABLE IF NOT EXISTS `yhcc_business_form`(

  `id` VARCHAR (50) NOT NULL,
  `num` VARCHAR (50),
  `name` VARCHAR (500),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建业种表
CREATE TABLE IF NOT EXISTS `yhcc_business_species`(

  `id` VARCHAR (50) NOT NULL,
  `num` VARCHAR (50),
  `business_form_id` VARCHAR (50),
  `name` VARCHAR (500),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建区间设置表
CREATE TABLE IF NOT EXISTS `yhcc_interval_conf`(

  `id` VARCHAR (50) NOT NULL,
  `num` VARCHAR (50),
  `dimension` int(11),
  `type` int(11),
  `business_form_id` VARCHAR (50),
  `business_species_id` VARCHAR (50),
  `yx` int(11),
  `lh` int(11),
  `ts` int(11),
  `hl` int(11),
  `ks` int(11),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建标准三角形表
CREATE TABLE IF NOT EXISTS `yhcc_triangle`(

  `id` VARCHAR (50) NOT NULL,
  `num` VARCHAR (50),
  `dimension` int(11),
  `business_form_id` VARCHAR (50),
  `business_species_id` VARCHAR (50),
  `probably_profits` decimal(18,2),
  `price` decimal(18,2),
  `rent_ratio` decimal(18,2),
  `gp_degree` decimal(18,2),
  `adapter` int(11),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建楼栋表
CREATE TABLE IF NOT EXISTS `yhcc_building`(

  `id` VARCHAR (50) NOT NULL,
  `num` VARCHAR (50),
  `company_id` VARCHAR (50),
  `project_id` VARCHAR (50),
  `name` VARCHAR (50),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建楼层表
CREATE TABLE IF NOT EXISTS `yhcc_floor`(

  `id` VARCHAR (50) NOT NULL,
  `num` VARCHAR (50),
  `company_id` VARCHAR (50),
  `project_id` VARCHAR (50),
  `building_id` VARCHAR (50),
  `name` VARCHAR (50),
  `location` VARCHAR (50),
  `acreage` decimal(18,2),
  `state` TINYINT (1),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建铺位表
CREATE TABLE IF NOT EXISTS `yhcc_room`(

  `id` VARCHAR (50) NOT NULL,
  `num` VARCHAR (50),
  `name` VARCHAR (50),
  `company_id` VARCHAR (50),
  `project_id` VARCHAR (50),
  `building_id` VARCHAR (50),
  `floor_id` VARCHAR (50),
  `state` int(11),
  `acreage` decimal(18,2),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建品牌表
CREATE TABLE IF NOT EXISTS `yhcc_brand`(

  `id` VARCHAR (50) NOT NULL,
  `num` VARCHAR (50),
  `name` VARCHAR (50),
  `business_form_id` VARCHAR (50),
  `business_species_id` VARCHAR (50),
  `brand_type` int(11),
  `state` int(11),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建公式表
CREATE TABLE IF NOT EXISTS `yhcc_formula`(

  `id` VARCHAR (50) NOT NULL,
  `num` VARCHAR (50),
  `target_id` VARCHAR (50),
  `target_name` VARCHAR (50),
  `operation_formula` VARCHAR (500),
  `result_formula` VARCHAR (500),
  `state` TINYINT (1),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建外部组织机构数据表
CREATE TABLE IF NOT EXISTS `yhcc_external_org`(

  `id` VARCHAR (100) NOT NULL,
  `name` VARCHAR (255),
  `parent_id` VARCHAR (100),
  `type` VARCHAR (50),
  `code` VARCHAR (50),
  `status` VARCHAR (10),
  `delflag` TINYINT (1),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建外部员工数据表
CREATE TABLE IF NOT EXISTS `yhcc_external_employee`(

  `id` VARCHAR (50) NOT NULL,
  `real_name` VARCHAR (50),
  `login_name` VARCHAR (50),
  `email` VARCHAR (50),
  `belong_org_id` VARCHAR (50),
  `mobile` VARCHAR (50),
  `status` VARCHAR (10),
  `delflag` TINYINT (1),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 创建签约表
CREATE TABLE IF NOT EXISTS `yhcc_contract`(

  `id` VARCHAR (50) NOT NULL,
  `project_id` VARCHAR (50),
  `floor_id` VARCHAR (50),
  `room_id` VARCHAR (50),
  `brand_id` VARCHAR (50),
  `status` TINYINT (1),
  `create_time` DATETIME,
  `modify_time` DATETIME,
  `create_user` VARCHAR (100),
  `modify_user` VARCHAR (100),
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;