/**
 * This is a setUp methods to select Chrome version	
 * @param browserVersion
 */
	public static void setUp(String browserVersion) {
		
		ChromeOptions options = new ChromeOptions();
		options.setBrowserVersion(browserVersion);

		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

		switch (ConfigsReader.getProperty("browser")) {
		case "chrome":
			driver = new ChromeDriver(options);
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			throw new RuntimeException("Other browser are NOT supported!!!");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME));
		driver.get(ConfigsReader.getProperty("url"));

	}

		/** Taking the screenshot of the whole page
	 * 
	 * @param fileName
	 */
	
	public static void screenshot(String fileName) {
	
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				File destination = new File("screenshots/"+fileName+".png");

				try {
					Files.copy(source, destination);
				} catch (IOException e) {
					System.out.println("Couldn't save the screenshot!");
					e.printStackTrace();
				}
	}
	
	/**
	 * Taking the screenshot of a WebElement
	 * @param element
	 * @param fileName
	 */
	
	public static void screenshot(WebElement element,String fileName) {
		
				TakesScreenshot ts = (TakesScreenshot) element;
				File source = ts.getScreenshotAs(OutputType.FILE);
				File destination = new File("screenshots/"+fileName);

				try {
					Files.copy(source, destination);
				} catch (IOException e) {
					System.out.println("Couldn't save the screenshot!");
					e.printStackTrace();
				}
	}