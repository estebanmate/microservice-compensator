package com.masmovil.it.compensator.service;

import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.Optional;

import javax.inject.Singleton;

import com.masmovil.it.compensator.model.Category;

@Singleton
public class CategoryService {

  protected final SalesForceQueryService salesForceQueryService;

  /**
   * Constructor.
   * @param salesForceQueryService Injected salesForceQueryService.
   */
  public CategoryService(SalesForceQueryService salesForceQueryService) {
    this.salesForceQueryService = salesForceQueryService;
  }

  /**
   * Get all categories.
   * @return Categories.
   */
  public Flowable<Category> getCategories() {
    return salesForceQueryService.categories()
      .filter(category -> category.getParentCategoryId() == null);
  }

  public Single<Category> category(String id) {
    return salesForceQueryService.categories()
      .filter(category -> id.equals(category.getId())).firstOrError();
  }

  /**
   * Get all subcategories.
   * @param subscriptionType Optional subscription type of the subcategory.
   * @return Subcategories.
   */
  public Flowable<Category> getAllSubcategoryList(Optional<String> subscriptionType) {
    return salesForceQueryService.categories()
      .filter(category ->
        category.getParentCategoryId() != null
        && category.checkSubscriptionType(subscriptionType)
      );
  }

  /**
   * Get the list of subcategories of a category.
   * @param id Id of the category.
   * @param subscriptionType Optional subscription type of the subcategory.
   * @return Subcategories.
   */
  public Flowable<Category> getSubcategoryList(String id, Optional<String> subscriptionType) {
    return salesForceQueryService.categories()
      .filter(category ->
        id.equals(category.getParentCategoryId())
        && category.checkSubscriptionType(subscriptionType)
      );
  }

  /**
   * Return a subcategory.
   * @param id Id of the subcategory.
   * @param subscriptionType Optional subscription type of the subcategory.
   * @return Subcategory.
   */
  public Single<Category> subcategory(String id, Optional<String> subscriptionType) {
    return salesForceQueryService.categories()
      .filter(category ->
        id.equals(category.getId())
        && category.checkSubscriptionType(subscriptionType)
      )
      .firstOrError();
  }

}