package com.prs.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.prs.business.PurchaseRequest;
import com.prs.business.User;

public class PurchaseRequestDB {

	public static List<PurchaseRequest> getAll() {
		List<PurchaseRequest> purchaseRequests = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		try {
			Query q = em.createQuery("Select p from PurchaseRequest p");
			purchaseRequests = q.getResultList();
		} finally {
			em.close();
		}

		return purchaseRequests;
	}

	public static PurchaseRequest get(int n) {
		PurchaseRequest purchaseRequest = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		TypedQuery<PurchaseRequest> q = em.createQuery("Select p from PurchaseRequest p Where p.id = :n", PurchaseRequest.class);
		q.setParameter("n", n);
		try {
			purchaseRequest = q.getSingleResult();
		} catch (NoResultException e) {
			System.err.println(e);
		} finally {
			em.close();
		}

		return purchaseRequest;
	}

	public static void insert(PurchaseRequest purchaseRequest) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(purchaseRequest);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(PurchaseRequest purchaseRequest) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(purchaseRequest);
			em.remove(purchaseRequest);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void update(PurchaseRequest pr) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(pr);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

}
