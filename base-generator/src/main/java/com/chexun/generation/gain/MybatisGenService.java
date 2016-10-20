package com.chexun.generation.gain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class MybatisGenService extends JdbcDaoSupport {
	@Getter
	@Setter
	private Map<String, List<String>> tableNSMap;
	@Getter
	@Setter
	private String packageName;
	@Getter
	@Setter
	private String ibatisFileDir;
	@Getter
	@Setter
	private static String destDir;
	@Getter
	@Setter
	private static String templateDir;
	@Getter
	@Setter
	private int preOrSu;
	@Getter
	@Setter
	private String separator;
	@Getter
	@Setter
	private List<NameSpaceVO> nsvoList;
	static {
		try {
			File directory = new File("");// 参数为空
			// 项目路径
			String courseFile = directory.getCanonicalPath();
			// 模版路径
			templateDir = courseFile + "/src/main/resources/gain/ftl/mybatis";
			destDir = courseFile + "/src/test/java";
		} catch (IOException e) {
		}

	}

	public void gen() throws Exception {
		List<TableVO> tableList = new ArrayList<TableVO>();
		Connection conn = null;
		ResultSet rs = null;
		ResultSet rsKey = null;
		ResultSet rsImpKey = null;
		ResultSet rsExtKey = null;
		try {
			conn = getDataSource().getConnection();
			DatabaseMetaData dmd = conn.getMetaData();
			rs = dmd.getTables(null, null, "%", new String[] { "TABLE","VIEW" });
			while (rs.next()) {
				TableVO tableVO = new TableVO();
				tableVO.setPreOrSu(preOrSu);
				tableVO.setSeparator(separator);
				String tableName = rs.getString(3);
				tableVO.setTableName(tableName);

				rsKey = dmd.getPrimaryKeys(null, null, tableName);
				while (rsKey.next()) {
					tableVO.getKeys().add(rsKey.getString(4));
				}

				rsExtKey = dmd.getExportedKeys(null, null, tableName);
				while (rsExtKey.next()) {
					System.out.println("ext key of " + tableName + ":"
							+ rsExtKey.getString(4));
				}

				rsImpKey = dmd.getImportedKeys(null, null, tableName);
				while (rsImpKey.next()) {
					System.out.println("imp key of " + tableName + ":"
							+ rsImpKey.getString(4));
				}

				List<ColumnVO> columnList = new ArrayList<ColumnVO>();
				String sql = "select * from " + rs.getString(3) + " where 1=2";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rsTable = ps.executeQuery();
				ResultSetMetaData rsmd = rsTable.getMetaData();
				int mdCnt = rsmd.getColumnCount();
				for (int i = 1; i <= mdCnt; i++) {
					ColumnVO columnVO = new ColumnVO();
					columnVO.setColumnName(rsmd.getColumnName(i));
					columnVO.setColumnType(rsmd.getColumnType(i));
					if (tableVO.getKeys().contains(columnVO.getColumnName())) {
						columnVO.setKey(true);
					}
					if (columnVO.getColumnName().toString()
							.equalsIgnoreCase("id")) {
						columnVO.setKey(true);
					}
					columnList.add(columnVO);
				}
				tableVO.setColumnList(columnList);
				tableList.add(tableVO);
				rsTable.close();
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
			if (rsKey != null)
				rsKey.close();
			if (rsImpKey != null)
				rsImpKey.close();
			if (rsExtKey != null)
				rsExtKey.close();
		}
		List<NameSpaceVO> nameSpaceVOList = inNSMap(tableList);

		genMybatisXmlFile(nameSpaceVOList);
		genJavaBeanClassFile(nameSpaceVOList);

		genDaoFile(nameSpaceVOList);
		genDaoImplFile(nameSpaceVOList);

		genServiceInterfaceFile(nameSpaceVOList);
		genServiceImplFile(nameSpaceVOList);
		genControllerFile(nameSpaceVOList);
		genListHTTlFile(nameSpaceVOList);
		genAddHTTlFile(nameSpaceVOList);
		genEditHTTlFile(nameSpaceVOList);
	}

	// xml
	public void genMybatisXmlFile(List<NameSpaceVO> nameSpaceVOList)
			throws Exception {
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		File templateDirdd = new File(templateDir);
		if (!templateDirdd.exists()) {
			templateDirdd.mkdirs();
		}

		cfg.setDirectoryForTemplateLoading(templateDirdd);
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		Template template = cfg.getTemplate("mybatisXml.ftl");

		for (NameSpaceVO nameSpaceVO : nameSpaceVOList) {
			for (TableVO tableVO : nameSpaceVO.getTableList()) {
				tableVO.setPackageName(packageName.replaceAll("\\\\", "."));

				String entityPackDir = "";
				if ("defalut".equalsIgnoreCase(nameSpaceVO.getName())) {
					entityPackDir = destDir + File.separator + ibatisFileDir
							+ File.separator;
				} else {
					entityPackDir = destDir + File.separator + ibatisFileDir
							+ File.separator + nameSpaceVO.getName();
				}
				File configFileDir = new File(entityPackDir);
				if (!configFileDir.exists()) {
					configFileDir.mkdirs();
				}
				String filePath = entityPackDir + File.separator
						+ tableVO.getVoClassName() + "Mapper.xml";

				File file = new File(filePath);
				Writer out = new OutputStreamWriter(new FileOutputStream(file),
						"UTF-8");
				template.process(tableVO, out);
				out.flush();
			}
		}
	}
	
	public void genListHTTlFile(List<NameSpaceVO> nameSpaceVOList)
			throws Exception {
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		File templateDirdd = new File(templateDir);
		if (!templateDirdd.exists()) {
			templateDirdd.mkdirs();
		}
		cfg.setDirectoryForTemplateLoading(templateDirdd);
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		Template template = cfg.getTemplate("list.ftl");
		for (NameSpaceVO nameSpaceVO : nameSpaceVOList) {
			for (TableVO tableVO : nameSpaceVO.getTableList()) {
				tableVO.setPackageName(packageName.replaceAll("\\\\", "."));

				String entityPackDir = "";
				if ("defalut".equalsIgnoreCase(nameSpaceVO.getName())) {
					entityPackDir = destDir + File.separator + ibatisFileDir
							+ File.separator;
				} else {
					entityPackDir = destDir + File.separator + ibatisFileDir
							+ File.separator + nameSpaceVO.getName();
				}
				File configFileDir = new File(entityPackDir);
				if (!configFileDir.exists()) {
					configFileDir.mkdirs();
				}
				String filePath = entityPackDir + File.separator
						+ tableVO.getVoClassName().toLowerCase() + "_list.httl";

				File file = new File(filePath);
				Writer out = new OutputStreamWriter(new FileOutputStream(file),
						"UTF-8");
				template.process(tableVO, out);
				out.flush();
			}
		}
	}
	//edit.httl
	public void genEditHTTlFile(List<NameSpaceVO> nameSpaceVOList)
			throws Exception {
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		File templateDirdd = new File(templateDir);
		if (!templateDirdd.exists()) {
			templateDirdd.mkdirs();
		}
		cfg.setDirectoryForTemplateLoading(templateDirdd);
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		Template template = cfg.getTemplate("edit.ftl");
		for (NameSpaceVO nameSpaceVO : nameSpaceVOList) {
			for (TableVO tableVO : nameSpaceVO.getTableList()) {
				tableVO.setPackageName(packageName.replaceAll("\\\\", "."));

				String entityPackDir = "";
				if ("defalut".equalsIgnoreCase(nameSpaceVO.getName())) {
					entityPackDir = destDir + File.separator + ibatisFileDir
							+ File.separator;
				} else {
					entityPackDir = destDir + File.separator + ibatisFileDir
							+ File.separator + nameSpaceVO.getName();
				}
				File configFileDir = new File(entityPackDir);
				if (!configFileDir.exists()) {
					configFileDir.mkdirs();
				}
				String filePath = entityPackDir + File.separator
						+ tableVO.getVoClassName().toLowerCase() + "_edit.httl";

				File file = new File(filePath);
				Writer out = new OutputStreamWriter(new FileOutputStream(file),
						"UTF-8");
				template.process(tableVO, out);
				out.flush();
			}
		}
	}
	
	//add.httl
	public void genAddHTTlFile(List<NameSpaceVO> nameSpaceVOList)
			throws Exception {
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		File templateDirdd = new File(templateDir);
		if (!templateDirdd.exists()) {
			templateDirdd.mkdirs();
		}
		cfg.setDirectoryForTemplateLoading(templateDirdd);
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		Template template = cfg.getTemplate("add.ftl");
		for (NameSpaceVO nameSpaceVO : nameSpaceVOList) {
			for (TableVO tableVO : nameSpaceVO.getTableList()) {
				tableVO.setPackageName(packageName.replaceAll("\\\\", "."));

				String entityPackDir = "";
				if ("defalut".equalsIgnoreCase(nameSpaceVO.getName())) {
					entityPackDir = destDir + File.separator + ibatisFileDir
							+ File.separator;
				} else {
					entityPackDir = destDir + File.separator + ibatisFileDir
							+ File.separator + nameSpaceVO.getName();
				}
				File configFileDir = new File(entityPackDir);
				if (!configFileDir.exists()) {
					configFileDir.mkdirs();
				}
				String filePath = entityPackDir + File.separator
						+ tableVO.getVoClassName().toLowerCase() + "_add.httl";

				File file = new File(filePath);
				Writer out = new OutputStreamWriter(new FileOutputStream(file),
						"UTF-8");
				template.process(tableVO, out);
				out.flush();
			}
		}
	}
	// JavaBean
	public void genJavaBeanClassFile(List<NameSpaceVO> nameSpaceVOList)
			throws Exception {
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		cfg.setDirectoryForTemplateLoading(new File(templateDir));
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		Template template = cfg.getTemplate("javaBean.ftl");

		File voPackageDir = new File(destDir + File.separator + packageName
				+ File.separator + "model");
		if (!voPackageDir.exists()) {
			voPackageDir.mkdirs();
		}

		for (NameSpaceVO nameSpaceVO : nameSpaceVOList) {
			for (TableVO tableVO : nameSpaceVO.getTableList()) {
				tableVO.setPackageName(packageName.replaceAll("\\\\", "."));
				String entityPackDir = "";
				if ("defalut".equalsIgnoreCase(nameSpaceVO.getName())) {
					entityPackDir = destDir + File.separator + packageName
							+ File.separator + "model";
				} else {
					entityPackDir = destDir + File.separator + packageName
							+ File.separator + "model"+ File.separator + nameSpaceVO.getName();
				}
				voPackageDir = new File(entityPackDir);
				if (!voPackageDir.exists()) {
					voPackageDir.mkdirs();
				}

				String filePath = entityPackDir + File.separator
						+ tableVO.getVoClassName() + ".java";

				File file = new File(filePath);
				Writer out = new OutputStreamWriter(new FileOutputStream(file),
						"UTF-8");
				template.process(tableVO, out);
				out.flush();
			}
		}
	}

	// Service
	public void genServiceInterfaceFile(List<NameSpaceVO> nameSpaceVOList)
			throws Exception {
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("utf-8");
		cfg.setDirectoryForTemplateLoading(new File(templateDir));
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		Template template = cfg.getTemplate("javaService.ftl");
		template.setEncoding("utf-8");

		File voPackageDir = new File(destDir + File.separator + packageName
				+ File.separator + "service");
		if (!voPackageDir.exists()) {
			voPackageDir.mkdirs();
		}

		for (NameSpaceVO nameSpaceVO : nameSpaceVOList) {
			for (TableVO tableVO : nameSpaceVO.getTableList()) {
				tableVO.setPackageName(packageName.replaceAll("\\\\", "."));

				String entityPackDir = "";
				if ("defalut".equalsIgnoreCase(nameSpaceVO.getName())) {
					entityPackDir = destDir + File.separator + packageName
							+ File.separator + "service";
				} else {
					entityPackDir = destDir + File.separator + packageName
							 + File.separator + "service"+ File.separator + nameSpaceVO.getName();
				}
				voPackageDir = new File(entityPackDir);
				if (!voPackageDir.exists()) {
					voPackageDir.mkdirs();
				}

				String filePath = entityPackDir + File.separator
						+ tableVO.getVoClassName() + "Service.java";

				File file = new File(filePath);
				Writer out = new OutputStreamWriter(new FileOutputStream(file),
						"utf-8");
				template.process(tableVO, out);
				out.flush();
			}
		}
	}

	// ServiceImpl
	public void genServiceImplFile(List<NameSpaceVO> nameSpaceVOList)
			throws Exception {
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("utf-8");
		cfg.setDirectoryForTemplateLoading(new File(templateDir));
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		Template template = cfg.getTemplate("javaServiceImpl.ftl");
		template.setEncoding("utf-8");

		File voPackageDir = new File(destDir + File.separator + packageName
				+ File.separator + "service" + File.separator + "impl");
		if (!voPackageDir.exists()) {
			voPackageDir.mkdirs();
		}

		for (NameSpaceVO nameSpaceVO : nameSpaceVOList) {
			for (TableVO tableVO : nameSpaceVO.getTableList()) {
				tableVO.setPackageName(packageName.replaceAll("\\\\", "."));
				String entityPackDir = "";
				if ("defalut".equalsIgnoreCase(nameSpaceVO.getName())) {
					entityPackDir = destDir + File.separator + packageName
							+ File.separator + "service" + File.separator
							+ "impl";
				} else {
					entityPackDir = destDir + File.separator + packageName
							+ File.separator
							+ "service" + File.separator + "impl" + File.separator + nameSpaceVO.getName();
				}
				voPackageDir = new File(entityPackDir);
				if (!voPackageDir.exists()) {
					voPackageDir.mkdirs();
				}

				String filePath = entityPackDir + File.separator
						+ tableVO.getVoClassName() + "ServiceImpl.java";

				File file = new File(filePath);
				Writer out = new OutputStreamWriter(new FileOutputStream(file),
						"utf-8");
				template.process(tableVO, out);
				out.flush();
			}
		}
	}

	// Dao
	public void genDaoFile(List<NameSpaceVO> nameSpaceVOList) throws Exception {
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("utf-8");
		cfg.setDirectoryForTemplateLoading(new File(templateDir));
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		Template template = cfg.getTemplate("javaDao.ftl");
		template.setEncoding("utf-8");

		File voPackageDir = new File(destDir + File.separator + packageName
				+ File.separator + "dao");
		if (!voPackageDir.exists()) {
			voPackageDir.mkdirs();
		}

		for (NameSpaceVO nameSpaceVO : nameSpaceVOList) {
			for (TableVO tableVO : nameSpaceVO.getTableList()) {
				tableVO.setPackageName(packageName.replaceAll("\\\\", "."));

				String entityPackDir = "";
				if ("defalut".equalsIgnoreCase(nameSpaceVO.getName())) {
					entityPackDir = destDir + File.separator + packageName
							+ File.separator + "dao";
				} else {
					entityPackDir = destDir + File.separator + packageName
							+ File.separator
							+ "dao" + File.separator + nameSpaceVO.getName();
				}
				voPackageDir = new File(entityPackDir);
				if (!voPackageDir.exists()) {
					voPackageDir.mkdirs();
				}

				String filePath = entityPackDir + File.separator
						+ tableVO.getVoClassName() + "Dao.java";

				File file = new File(filePath);
				Writer out = new OutputStreamWriter(new FileOutputStream(file),
						"utf-8");
				template.process(tableVO, out);
				out.flush();
			}
		}
	}

	// DaoImp
	public void genDaoImplFile(List<NameSpaceVO> nameSpaceVOList)
			throws Exception {
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("utf-8");
		cfg.setDirectoryForTemplateLoading(new File(templateDir));
		cfg.setObjectWrapper(new DefaultObjectWrapper());

		Template template = cfg.getTemplate("javaDaoImpl.ftl");
		template.setEncoding("utf-8");

		File voPackageDir = new File(destDir + File.separator + packageName
				+ File.separator + "dao" + File.separator + "impl");
		if (!voPackageDir.exists()) {
			voPackageDir.mkdirs();
		}

		for (NameSpaceVO nameSpaceVO : nameSpaceVOList) {
			for (TableVO tableVO : nameSpaceVO.getTableList()) {
				tableVO.setPackageName(packageName.replaceAll("\\\\", "."));

				String entityPackDir = "";
				if ("defalut".equalsIgnoreCase(nameSpaceVO.getName())) {
					entityPackDir = destDir + File.separator + packageName
							+ File.separator + "dao" + File.separator + "impl";
				} else {
					entityPackDir = destDir + File.separator + packageName
							+ File.separator + "dao"
							+ File.separator + "impl" + File.separator + nameSpaceVO.getName();
				}
				voPackageDir = new File(entityPackDir);
				if (!voPackageDir.exists()) {
					voPackageDir.mkdirs();
				}

				String filePath = entityPackDir + File.separator
						+ tableVO.getVoClassName() + "DaoImpl.java";

				File file = new File(filePath);
				Writer out = new OutputStreamWriter(new FileOutputStream(file),
						"utf-8");
				template.process(tableVO, out);
				out.flush();
			}
		}
	}
	
	// DaoImp
		public void genControllerFile(List<NameSpaceVO> nameSpaceVOList)
				throws Exception {
			Configuration cfg = new Configuration();
			cfg.setDefaultEncoding("utf-8");
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			Template template = cfg.getTemplate("controller.ftl");
			template.setEncoding("utf-8");

			File voPackageDir = new File(destDir + File.separator + packageName
					+ File.separator + "controller");
			if (!voPackageDir.exists()) {
				voPackageDir.mkdirs();
			}

			for (NameSpaceVO nameSpaceVO : nameSpaceVOList) {
				for (TableVO tableVO : nameSpaceVO.getTableList()) {
					tableVO.setPackageName(packageName.replaceAll("\\\\", "."));

					String entityPackDir = "";
					if ("defalut".equalsIgnoreCase(nameSpaceVO.getName())) {
						entityPackDir = destDir + File.separator + packageName
								+ File.separator + "controller";
					} else {
						entityPackDir = destDir + File.separator + packageName
								+ File.separator + "controller"
								+ File.separator + nameSpaceVO.getName();
					}
					voPackageDir = new File(entityPackDir);
					if (!voPackageDir.exists()) {
						voPackageDir.mkdirs();
					}

					String filePath = entityPackDir + File.separator
							+ tableVO.getVoClassName() + "Controller.java";

					File file = new File(filePath);
					Writer out = new OutputStreamWriter(new FileOutputStream(file),
							"utf-8");
					template.process(tableVO, out);
					out.flush();
				}
			}
		}

	public List<NameSpaceVO> inNSMap(List<TableVO> tableList) {
		List<NameSpaceVO> nameSpaceVOList = new ArrayList<NameSpaceVO>();
		List<TableVO> defaultTalbeVOList = new ArrayList<TableVO>();
		for (String nameSpaceName : tableNSMap.keySet()) {
			NameSpaceVO nameSpaceVO = new NameSpaceVO();
			nameSpaceVO.setName(nameSpaceName);
			for (TableVO tableVO : tableList) {

				if (tableNSMap.get(nameSpaceName).contains(
						tableVO.getTableName())) {

					tableVO.setMypackageName(nameSpaceName);
					nameSpaceVO.getTableList().add(tableVO);
				}
			}
			nameSpaceVOList.add(nameSpaceVO);
		}
		for (String nameSpaceName : tableNSMap.keySet()) {
			for (TableVO tableVO : tableList) {
				if (tableNSMap.get(nameSpaceName).contains(
						tableVO.getTableName())) {
					if (defaultTalbeVOList.contains(tableVO)) {
						defaultTalbeVOList.remove(tableVO);
					}
				} else {
					defaultTalbeVOList.add(tableVO);
				}
			}
		}
		/**
		if (defaultTalbeVOList.size() > 0) {
			NameSpaceVO nameSpaceVO = new NameSpaceVO();
			nameSpaceVO.setName("default");
			nameSpaceVO.setTableList(defaultTalbeVOList);
			nameSpaceVOList.add(nameSpaceVO);
		}*/

		return nameSpaceVOList;
	}

}
