# Supabase Backend Configuration — Campus Lost & Found

This directory contains the backend setup guidelines and environment definitions for the **Campus Lost & Found** application.

---

## 1. Supabase Project Setup Instructions

To configure the Supabase cloud project for this application, perform the following steps on the Supabase Dashboard ([https://supabase.com](https://supabase.com)):

### A. Project Creation
1. Sign in to your **Supabase Console**.
2. Click **New Project** and select your Organization.
3. Set **Name:** `Campus Lost & Found`.
4. Generate or specify a strong **Database Password**.
5. Select the **Region** closest to your target campus user base.
6. Click **Create new project**.

---

## 2. Supabase Auth Configuration
1. In the Supabase Dashboard, go to **Authentication** -> **Providers**.
2. Ensure **Email** authentication is **Enabled**.
3. (Optional) Configure student email domain restrictions if required under **Auth Settings**.

---

## 3. PostgreSQL Database Environment Setup
1. In the Supabase Dashboard, navigate to **Project Settings** -> **Database**.
2. Ensure PostgreSQL extensions `uuid-ossp` and `pgcrypto` are available.
3. Database timezone is set to `UTC`.
4. *Note: Table creation, schemas, indexes, foreign keys, and RLS policies will be created in Task 4 (Database Design) and Task 5 (Supabase Security).*

---

## 4. Supabase Storage Setup
1. Go to **Storage** in the Supabase Dashboard.
2. Prepare a bucket named `item-images`:
   - **Public bucket:** Enabled
   - **Allowed MIME types:** `image/jpeg`, `image/png`, `image/webp`
   - **Max file size:** `5MB`

---

## 5. Supabase Realtime Setup
1. Go to **Database** -> **Publications**.
2. Ensure `supabase_realtime` publication is enabled for listening to database events.

---

## 6. Environment Credentials Management

1. Go to **Project Settings** -> **API**.
2. Retrieve your:
   - **Project URL** (`SUPABASE_URL`)
   - **Anon Key** (`SUPABASE_ANON_KEY` / `API Key - public`)
3. **DO NOT** commit these keys into Git.
4. Keep these values ready for **Task 1.10 (Connect Android app to Supabase)** where local property injection will be configured safely.
