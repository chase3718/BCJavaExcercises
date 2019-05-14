package com.prs.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.prs.business.PurchaseRequestLineItem;
import com.prs.business.User;

public class PurchaseRequestLineItemDB {

	public static List<PurchaseRequestLineItem> getAll() {
		List<PurchaseRequestLineItem> purchaseRequestLineItems = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		try {
			Query q = em.createQuery("Select p from PurchaseRequestLineItem p");
			purchaseRequestLineItems = q.getResultList();
		} finally {
			em.close();
		}

		return purchaseRequestLineItems;
	}

	public static PurchaseRequestLineItem get(int n) {
		PurchaseRequestLineItem purchaseRequestLineItem = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		TypedQuery<PurchaseRequestLineItem> q = em.createQuery("Select p from PurchaseRequestLineItem p Where p.id = :n", PurchaseRequestLineItem.class);
		q.setParameter("n", n);
		try {
			purchaseRequestLineItem = q.getSingleResult();
		} catch (NoResultException e) {
			System.err.println(e);
		} finally {
			em.close();
		}

		return purchaseRequestLineItem;
	}

	public static void insert(PurchaseRequestLineItem purchaseRequestLineItem) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(purchaseRequestLineItem);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(PurchaseRequestLineItem purchaseRequestLineItem) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(purchaseRequestLineItem);
			em.remove(purchaseRequestLineItem);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void update(PurchaseRequestLineItem prl) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(prl);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

}
