from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
import time
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait


options = Options()
options.add_argument("--start-maximized")
options.add_experimental_option("detach",True)

driver = webdriver.Chrome(options=options)

url = "https://gw.datastreams.co.kr/"

driver.get(url)
time.sleep(1)

# 1. ID입력
loginid = driver.find_element(By.NAME, "loginid")
loginid.send_keys("donghwan.kim")

# 2. PASSWD 입력
password = driver.find_element(By.NAME, "password")
password.send_keys("dhkim0014*")

# 3. 로그인버튼 입력
time.sleep(1)
loginButton = driver.find_element(By.CSS_SELECTOR, ".button-ds-primary")
loginButton.click()
time.sleep(10)


# 4. 상단 프로젝트 클릭
time.sleep(3)
projectLink = driver.find_element(By.LINK_TEXT, '프로젝트')
projectLink.click()


# 4. 좌측 프로젝트비용 클릭
time.sleep(3)
parent_element = driver.find_element(By.XPATH, "//a[contains(., '프로젝트비용')]")
parent_element.click()


# 5. 좌측 프로젝트비용 > 비용관리 클릭
time.sleep(1)
kdh = driver.find_element(By.LINK_TEXT, '비용관리')
kdh.click()


# 6. 월 항목 클릭 후 06 키인 후 엔터 
# 몰루 스킵 ㅋ

# 7. 등록버튼 클릭

time.sleep(5)

try:
    # 버튼을 찾을 때까지 대기 (최대 10초)
    button = WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.XPATH, "//button[text()='등록']"))
    )

    # 버튼이 클릭 가능한지 확인 후 클릭
    if button.is_enabled():
        button.click()
    else:
        print("버튼이 클릭 가능하지 않습니다.")

except Exception as e:
    print(f"에러 발생: {e}")

finally:
    # WebDriver 종료
    print("끝남")


