document.addEventListener("DOMContentLoaded", loadTicketList);

async function loadTicketList() {
  const statusEl = document.getElementById("listStatus");
  const listEl = document.getElementById("ticketList");

  try {
    const tickets = await fetchJson("/tickets");
    listEl.innerHTML = "";

    if (!tickets.length) {
      statusEl.textContent = "No tickets yet.";
      return;
    }

    statusEl.textContent = `${tickets.length} ticket(s)`;

    tickets.forEach((ticket) => {
      listEl.appendChild(createTicketListItem(ticket));
    });
  } catch (error) {
    statusEl.classList.add("error");
    console.error(error);
  }
}

function createTicketListItem(ticket) {
  const li = document.createElement("li");
  li.className = "ticket-list-item";

  const ellipsisMenu = document.createElement("button");
  ellipsisMenu.className = "ellipsis-menu";
  ellipsisMenu.innerText = "...";

  const link = document.createElement("a");
  link.className = "ticket-list-link";
  link.href = `pages/ticket-page.html?ticketId=${encodeURIComponent(ticket.ticketId)}`;

  const title = document.createElement("h2");
  title.className = "ticket-list-title";
  title.textContent = ticket.ticketId + ": " + (ticket.ticketTitle ?? "Untitled");

  const meta = document.createElement("div");
  meta.className = "ticket-list-meta";
  meta.innerHTML = `
    <span class="badge badge-status">${"Status: " +formatLabel(ticket.ticketStatus)}</span>
    <span class="badge ${priorityClass(ticket.ticketPriority)}">${"Priority: " + formatLabel(ticket.ticketPriority)}</span>
    <span>Owner: ${getTicketOwnerUsername(ticket)}</span>
  `;

  link.append(title, meta);
  li.appendChild(link);
  li.appendChild(ellipsisMenu);
  return li;
}

function priorityClass(priority) {
  const value = String(priority ?? "").toLowerCase();
  if (value === "low") return "badge-priority-low";
  if (value === "high") return "badge-priority-high";
  return "badge-priority-medium";
}
