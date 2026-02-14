import type { ReactNode } from "react";

export default function DefaultLayout(
	props: Readonly<{ children: ReactNode }>
) {
	return <main className="px-300">{props.children}</main>;
}
