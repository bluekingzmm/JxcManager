package com.lzw;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.lzw.login.Login;
public class JXCFrame {
	private JPanel sysManagePanel;//系统管理面板
	private JDesktopPane desktopPane;//桌面面板
	private JFrame frame;//窗体（容器）
	private JLabel backLabel;//背景标签
	/*
	 * JLabel 对象可以显示文本、图像或同时显示二者。可以通过设置垂直和水平对齐方式，
	 * 指定标签显示区中标签内容在何处对齐。默认情况下，标签在其显示区内垂直居中对齐。
	 * 默认情况下，只显示文本的标签是开始边对齐；而只显示图像的标签则水平居中对齐。
	 *  还可以指定文本相对于图像的位置。默认情况下，文本位于图像的结尾边上，文本和图像都垂直对齐。 
	 */
	// 创建窗体的Map类型集合对象,内部窗口
	private Map<String, JInternalFrame> ifs = new HashMap<String, JInternalFrame>();
	public JXCFrame() {
		frame = new JFrame("企业进销存管理系统");//创建窗体对象
		frame.getContentPane().setBackground(new Color(170, 188, 120));//设置颜色，RGB颜色对照表
		frame.addComponentListener(new FrameListener());//添加窗体事件监听器
		frame.getContentPane().setLayout(new BorderLayout());//布局管理器
		frame.setBounds(200, 200, 800, 600);//设置窗体位置和大小，前两个数是坐标，后面是宽高
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体默认的关闭方式
		backLabel = new JLabel();// 背景标签
		backLabel.setVerticalAlignment(SwingConstants.TOP);//设置背景标签垂直对齐方式
		backLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置背景标签水平对齐方式
		updateBackImage(); // 更新或初始化背景图片
		desktopPane = new JDesktopPane();//创建桌面面板
		desktopPane.add(backLabel, new Integer(Integer.MIN_VALUE));//将背景标签添加到桌面面板中
		frame.getContentPane().add(desktopPane);//添加桌面面板到窗体
		JTabbedPane navigationPanel = createNavigationPanel(); // 创建导航标签面板
		frame.getContentPane().add(navigationPanel, BorderLayout.NORTH);//添加导航标签到窗体
		frame .setVisible(true);//显示窗体
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Login();
			}
		});
	}
	private JTabbedPane createNavigationPanel() { // 创建导航标签面板的方法
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFocusable(false);
		/*
		 * 
                     就是失去焦点了。所谓焦点就是被选中的意思，或者说是“当前正在操作的组件”的意思。
                     如果一个组件被选中，或者正在被操作者，就是得到了焦点，而相反的，
                     一个组件没有被选中或者失去操作，就是被转移了焦点，焦点已经到别的组件上去了。
		 */
		tabbedPane.setBackground(new Color(211, 230, 192));
		//边框为双线斜面边框（浮雕效果）
		tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED));

		JPanel baseManagePanel = new JPanel(); // 基础信息管理面板
		baseManagePanel.setBackground(new Color(215, 223, 194));
		baseManagePanel.setLayout(new BoxLayout(baseManagePanel,
				BoxLayout.X_AXIS));
		//setLayout()设置用户界面上的屏幕组件的格式布局.BoxLayout允许垂直或水平布置多个组件的布局管理器。
		
		//BoxLayout.X_AXIS：从左到右水平布置组件,BoxLayout.Y_AXIS: 从上到下垂直布置组件
		baseManagePanel.add(createFrameButton("客户信息管理", "KeHuGuanLi"));
		baseManagePanel.add(createFrameButton("商品信息管理", "ShangPinGuanLi"));
		baseManagePanel.add(createFrameButton("供应商信息管理", "GysGuanLi"));

		JPanel depotManagePanel = new JPanel(); // 库存管理面板
		depotManagePanel.setBackground(new Color(215, 223, 194));
		depotManagePanel.setLayout(new BoxLayout(depotManagePanel,
				BoxLayout.X_AXIS));
		depotManagePanel.add(createFrameButton("库存盘点", "KuCunPanDian"));
		depotManagePanel.add(createFrameButton("价格调整", "JiaGeTiaoZheng"));

		JPanel sellManagePanel = new JPanel();// 销售管理面板
		sellManagePanel.setBackground(new Color(215, 223, 194));
		sellManagePanel.setLayout(new BoxLayout(sellManagePanel,
				BoxLayout.X_AXIS));
		sellManagePanel.add(createFrameButton("销售单", "XiaoShouDan"));
		sellManagePanel.add(createFrameButton("销售退货", "XiaoShouTuiHuo"));
 
		JPanel searchStatisticPanel = new JPanel();// 查询统计面板
		searchStatisticPanel.setBounds(0, 0, 600, 41);
		searchStatisticPanel.setName("searchStatisticPanel");
		searchStatisticPanel.setBackground(new Color(215, 223, 194));
		searchStatisticPanel.setLayout(new BoxLayout(searchStatisticPanel,
				BoxLayout.X_AXIS));
		searchStatisticPanel.add(createFrameButton("客户信息查询", "KeHuChaXun"));
		searchStatisticPanel.add(createFrameButton("商品信息查询", "ShangPinChaXun"));
		searchStatisticPanel.add(createFrameButton("供应商信息查询",
				"GongYingShangChaXun"));
		searchStatisticPanel.add(createFrameButton("销售信息查询", "XiaoShouChaXun"));
		searchStatisticPanel.add(createFrameButton("销售退货查询",
				"XiaoShouTuiHuoChaXun"));
		searchStatisticPanel.add(createFrameButton("入库查询", "RuKuChaXun"));
		searchStatisticPanel
				.add(createFrameButton("入库退货查询", "RuKuTuiHuoChaXun"));
		searchStatisticPanel.add(createFrameButton("销售排行", "XiaoShouPaiHang"));

		JPanel stockManagePanel = new JPanel();// 进货管理面板
		stockManagePanel.setBackground(new Color(215, 223, 194));
		stockManagePanel.setLayout(new BoxLayout(stockManagePanel,
				BoxLayout.X_AXIS));
		stockManagePanel.add(createFrameButton("进货单", "JinHuoDan"));
		stockManagePanel.add(createFrameButton("进货退货", "JinHuoTuiHuo"));

		sysManagePanel = new JPanel();// 系统管理面板
		sysManagePanel.setBackground(new Color(215, 223, 194));
		sysManagePanel
				.setLayout(new BoxLayout(sysManagePanel, BoxLayout.X_AXIS));
		sysManagePanel.add(createFrameButton("操作员管理", "CzyGL"));
		sysManagePanel.add(createFrameButton("更改密码", "GengGaiMiMa"));
		sysManagePanel.add(createFrameButton("权限管理", "QuanManager"));

		tabbedPane.addTab("   基础信息管理   ", null, baseManagePanel, "基础信息管理");
		tabbedPane.addTab("   进货管理   ", null, stockManagePanel, "进货管理");
		tabbedPane.addTab("   销售管理   ", null, sellManagePanel, "销售管理");
		tabbedPane.addTab("   查询统计   ", null, searchStatisticPanel, "查询统计");
		tabbedPane.addTab("   库存管理   ", null, depotManagePanel, "库存管理");
		tabbedPane.addTab("   系统管理   ", null, sysManagePanel, "系统管理");

		return tabbedPane;
	}
	/** *********************辅助方法************************* */
	// 为内部窗体添加Action的方法
	private JButton createFrameButton(String fName, String cname) {
		String imgUrl = "res/ActionIcon/" + fName + ".png";
		String imgUrl_roll = "res/ActionIcon/" + fName	+ "_roll.png";
		String imgUrl_down = "res/ActionIcon/" + fName	+ "_down.png";
		Icon icon = new ImageIcon(imgUrl);
		Icon icon_roll = null;
		if (imgUrl_roll != null)
			icon_roll = new ImageIcon(imgUrl_roll);
		Icon icon_down = null;
		if (imgUrl_down != null)
			icon_down = new ImageIcon(imgUrl_down);
		Action action = new openFrameAction(fName, cname, icon);
		JButton button = new JButton(action);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setHideActionText(true);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		if (icon_roll != null)
			button.setRolloverIcon(icon_roll);
		if (icon_down != null)
			button.setPressedIcon(icon_down);
		return button;
	}
	// 获取内部窗体的唯一实例对象
	private JInternalFrame getIFrame(String frameName) {
		JInternalFrame jf = null;
		if (!ifs.containsKey(frameName)) {
			try {
				Class fClass = Class.forName("internalFrame." + frameName);
				Constructor constructor = fClass.getConstructor(null);
				jf = (JInternalFrame) constructor.newInstance(null);
				ifs.put(frameName, jf);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			jf = ifs.get(frameName);
		return jf;
	}
	// 更新背景图片的方法
	/*
	 * 初始化背景标签，背景标签使用的是html超文本语言设置了主窗体的背景图片
	 * 该图片将随着主窗体的大小自动收缩
	 */
	private void updateBackImage() {
		if (backLabel != null) {
			int backw = JXCFrame.this.frame.getWidth();
			int backh = frame.getHeight();
			backLabel.setSize(backw, backh);
			backLabel.setText("<html><body><image width='" + backw
					+ "' height='" + (backh - 110) + "' src="
					+ JXCFrame.this.getClass().getResource("welcome.jpg")
					+ "'></img></body></html>");
		}
	}
	// 窗体监听器
	private final class FrameListener extends ComponentAdapter {
		public void componentResized(final ComponentEvent e) {
			updateBackImage();
		}
	}
	// 主窗体菜单项的单击事件监听器
	protected final class openFrameAction extends AbstractAction {
		private String frameName = null;
		private openFrameAction() {
		}
		public openFrameAction(String cname, String frameName, Icon icon) {
			this.frameName = frameName;
			putValue(Action.NAME, cname);
			putValue(Action.SHORT_DESCRIPTION, cname);
			putValue(Action.SMALL_ICON, icon);
		}
		public void actionPerformed(final ActionEvent e) {
			JInternalFrame jf = getIFrame(frameName);
			// 在内部窗体闭关时，从内部窗体容器ifs对象中清除该窗体。
			jf.addInternalFrameListener(new InternalFrameAdapter() {
				public void internalFrameClosed(InternalFrameEvent e) {
					ifs.remove(frameName);
				}
			});
			if (jf.getDesktopPane() == null) {
				desktopPane.add(jf);
				jf.setVisible(true);
			}
			try {
				jf.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
	}
	//静态代码块只执行一次，类加载之前就执行了
	/*
	 * 设置外观样式，UIManager类中的getSystemLookAndFeel方法设置程序界面使用本地外观
	 */
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}