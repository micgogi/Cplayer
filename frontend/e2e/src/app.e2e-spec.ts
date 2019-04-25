import { AppPage } from './app.po';
import { browser, by, element, protractor } from 'protractor';
import { async } from 'q';


describe('CPlayer', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should show app name', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('CPlayer');
  });

  it('should be redirected to login page', () => {
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should redirect route', () => {
    browser.element(by.css('.register-button')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be able to register user', () => {
    browser.element(by.id('firstName')).sendKeys('amit1');
    browser.element(by.id('lastName')).sendKeys('gogyani1');
    browser.element(by.id('userId')).sendKeys(15020);
    browser.element(by.id('password')).sendKeys('151095rahul');
    browser.element(by.css('.register-user')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
  });
  

  it('should be able to login user androte to search page', () => {
  
    browser.element(by.id('userId')).sendKeys(15020);
    browser.element(by.id('password')).sendKeys('151095rahul');
    browser.element(by.css('.login-user')).click();
    expect(browser.getCurrentUrl()).toContain('/players/search');
  });

  it('should be able to search players', () => {
    browser.element(by.id('search')).click();
    browser.element(by.id('searchInput')).sendKeys('sachin');
    browser.element(by.id('searchInput')).sendKeys(protractor.Key.ENTER);
    const searchItems = element.all(by.id('player-name'));
    expect(searchItems.count()).toBe(25);
    for(let i = 0; i < 1; i += 1) {
      expect(searchItems.get(i).getText()).toContain('Sachin Rana');
    }
  });

  it('should be able to add palyer to favourite list', async() => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const searchItems = element.all(by.css('.player-thumbnail'));
    expect(searchItems.count()).toBe(25);
    searchItems.get(0).click();
    browser.element(by.id('add-button')).click();
    browser.driver.sleep(5000);
  });
  it('should be able to dlete',async()=>{
    browser.driver.manage().window().maximize();
    browser.element(by.id('favlist')).click();
  
  expect(browser.getCurrentUrl).toContain('players/FavList');
  browser.element(by.id('delete')).click();
  const del =element.all(by.id('player-name'));
  expect(del.count()).toBe(0)
  });



 

 
});
