package com.prs.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.prs.business.Product;
import com.prs.business.User;

public class ProductDB {

	public static List<Product> getAll() {
		List<Product> products = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		try {
			Query q = em.createQuery("Select p from Product p");
			products = q.getResultList();
		} finally {
			em.close();
		}

		return products;
	}

	public static Product get(int n) {
		Product product = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		TypedQuery<Product> q = em.createQuery("Select p from Product p Where p.id = :n", Product.class);
		q.setParameter("n", n);
		try {
			product = q.getSingleResult();
		} catch (NoResultException e) {
			System.err.println(e);
		} finally {
			em.close();
		}

		return product;
	}

	public static void insert(Product product) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(product);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Product product) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(product);
			em.remove(product);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void update(Product product) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(product);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

}
